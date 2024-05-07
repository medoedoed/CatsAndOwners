package ru.medoedoed.services.AuthServices;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.medoedoed.models.AuthEntities.JwtAuthenticationResponse;
import ru.medoedoed.models.AuthEntities.SignInRequest;
import ru.medoedoed.models.AuthEntities.SignUpRequest;
import ru.medoedoed.models.DataEntities.UserDto;
import ru.medoedoed.services.concreteServices.UserService;
import ru.medoedoed.utils.UserRole;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserService userService;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  public JwtAuthenticationResponse signUp(SignUpRequest request) {
    var userData = UserDto.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(UserRole.user).
            build();
    userService.save(userData);
    var user = userService.getByUsername(request.getUsername());
    var jwt = jwtService.generateToken(user);
    return new JwtAuthenticationResponse(jwt);
  }

  public JwtAuthenticationResponse signIn(SignInRequest request) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()
    ));

    var user = userService
            .userDetailsService()
            .loadUserByUsername(request.getUsername());

    var jwt = jwtService.generateToken(user);
    return new JwtAuthenticationResponse(jwt);
  }
}
