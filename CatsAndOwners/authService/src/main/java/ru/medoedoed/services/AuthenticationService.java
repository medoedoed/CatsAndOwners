package ru.medoedoed.services;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.medoedoed.models.authModels.*;
import ru.medoedoed.models.dataModels.UserDto;
import ru.medoedoed.role.Role;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final RabbitTemplate rabbitTemplate;
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
    //TODO
    Long userId = (Long) rabbitTemplate.convertSendAndReceive("user.register", userData);
    return jwtService.generateToken(userId);
  }

  public String signIn(SignInRequest request) {
    UserDto user = userService.getByUsername(request.getUsername());
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
