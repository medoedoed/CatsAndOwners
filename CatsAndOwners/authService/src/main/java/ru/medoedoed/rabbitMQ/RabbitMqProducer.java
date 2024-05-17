package ru.medoedoed.rabbitMQ;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.medoedoed.dataModels.UserDto;

@Service
@RequiredArgsConstructor
public class RabbitMqProducer {
  private final RabbitTemplate rabbitTemplate;

  public Long sendRegistrationUserMessage(UserDto userDto) {
    return (Long) rabbitTemplate.convertSendAndReceive("user.register", userDto);
  }
}
