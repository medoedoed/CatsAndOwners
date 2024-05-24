package ru.medoedoed.utils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.medoedoed.models.dataModels.UserDto;
import ru.medoedoed.services.JwtService;
import ru.medoedoed.services.UserService;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final JwtService jwtService;
  private final UserService userService;

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {
    String BEARER_PREFIX = "Bearer ";
    String HEADER_NAME = "Authorization";
    var authHeader = request.getHeader(HEADER_NAME);

    if (!StringUtils.hasText(authHeader) || !authHeader.startsWith(BEARER_PREFIX)) {
      filterChain.doFilter(request, response);
      return;
    }

    var jwt = authHeader.substring(BEARER_PREFIX.length());
    var id = jwtService.extractId(jwt);

    if (!jwtService.isTokenValid(jwt, id)) {
      filterChain.doFilter(request, response);
      return;
    }

    UserDto user;

    try {
      user = userService.getById(id);
    } catch (Exception e) {
      filterChain.doFilter(request, response);
      return;
    }

    if (user == null) {
      filterChain.doFilter(request, response);
      return;
    }

    SecurityContext context = SecurityContextHolder.createEmptyContext();
    List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole().toString()));
    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    context.setAuthentication(authentication);
    SecurityContextHolder.setContext(context);

    filterChain.doFilter(request, response);
  }
}
