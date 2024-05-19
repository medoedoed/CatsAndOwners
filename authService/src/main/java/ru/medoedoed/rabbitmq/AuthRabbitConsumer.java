package ru.medoedoed.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.medoedoed.services.UserService;

@Component
@EnableRabbit
@RequiredArgsConstructor
public class AuthRabbitConsumer {
  private final UserService userService;

//  @RabbitListener(queues = "user.requestCurrentUser")
//  public Long sendCurrentUser() {
//    return userService.getCurrentUser().getId();
//  }
}
