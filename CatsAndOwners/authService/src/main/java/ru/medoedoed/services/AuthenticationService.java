package ru.medoedoed.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.medoedoed.authModels.SignInRequest;
import ru.medoedoed.authModels.SignUpRequest;
import ru.medoedoed.dataModels.UserDto;
import ru.medoedoed.role.Role;


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
            .ownerId(request.getOwnerId())
            .build();
    Long userId = userService.save(userData);
    return jwtService.generateToken(userId);
            .ownerId(request.getOwnerId());
  }

  public String signIn(SignInRequest request) {
    var user = userService.getByUsername(request.getUsername());
    if (user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      return jwtService.generateToken(user.getId());
    }
    
    return null;
  }

  public String signUpAdmin(SignUpRequest request) {
    var userData =
            UserDto.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.ADMIN_ROLE)
                    .build();
    Long userId = userService.save(userData);
    return jwtService.generateToken(userId);
  }
}
