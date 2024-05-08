package ru.medoedoed.services.AuthServices;

import lombok.RequiredArgsConstructor;
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
  
  public JwtAuthenticationResponse signUp(SignUpRequest request) {
    var userData = UserDto.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(UserRole.user).
            build();
    Long userId = userService.save(userData);
    var jwt = jwtService.generateToken(userId);
    return new JwtAuthenticationResponse(jwt);
  }

  public String signIn(SignInRequest request) {
    var user = userService.getByUsername(request.getUsername());
    if (user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      return jwtService.generateToken(user.getId());
    }
    return null;
  }
}
