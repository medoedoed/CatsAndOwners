package ru.medoedoed.services.authServices;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.medoedoed.models.authEntities.SignInRequest;
import ru.medoedoed.models.authEntities.SignUpRequest;
import ru.medoedoed.models.dataEntities.UserDto;
import ru.medoedoed.services.concreteCrudServices.UserService;
import ru.medoedoed.utils.Role;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserService userService;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;

  public String signUp(SignUpRequest request) {
    var userData =
        UserDto.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER_ROLE)
            .build();
    Long userId = userService.save(userData);
    return jwtService.generateToken(userId);
  }

  public String signIn(SignInRequest request) {
    var user = userService.getByUsername(request.getUsername());
    if (user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      return jwtService.generateToken(user.getId());
    }
    
    return null;
  }
}
