package ru.medoedoed.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.medoedoed.models.dataModels.OwnerDto;

@Service
public class OwnerRabbitProducer {
  private final RabbitTemplate rabbitTemplate;

  public OwnerRabbitProducer(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  public void saveOwner(OwnerDto ownerData) {
    rabbitTemplate.convertAndSend("owner.save", ownerData);
  }
}
