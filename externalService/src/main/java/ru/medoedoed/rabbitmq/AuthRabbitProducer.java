package ru.medoedoed.rabbitmq;


import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.medoedoed.models.dataModels.OwnerDto;

@Service
@RequiredArgsConstructor
public class AuthRabbitProducer {
  private final RabbitTemplate rabbitTemplate;

  public Long saveOwner(OwnerDto ownerData) {
    return (Long) rabbitTemplate.convertSendAndReceive("owner.save", ownerData);
  }
}
