package ru.medoedoed.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.medoedoed.models.dataModels.UserDto;

@Service
@RequiredArgsConstructor
public class RabbitMqProducer {
  private final RabbitTemplate rabbitTemplate;

  public Long sendRegistrationUserMessage(UserDto userDto) {
    return (Long) rabbitTemplate.convertSendAndReceive("user.register", userDto);
  }
}
