package ru.medoedoed.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMqProducer {
  private final RabbitTemplate rabbitTemplate;

  public Long getCurrentUser() {
    return (Long) rabbitTemplate.convertSendAndReceive("user.requestCurrentUser");
  }
}
