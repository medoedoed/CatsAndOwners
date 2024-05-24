package ru.medoedoed.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.medoedoed.models.dataModels.OwnerDto;
import ru.medoedoed.services.OwnerService;

@Component
@RequiredArgsConstructor
public class OwnerRabbitConsumer {
  private final OwnerService ownerService;

  @RabbitListener(queues = "owner.save")
  public Long saveOwner(OwnerDto ownerData) {
    return ownerService.save(ownerData);
  }
}
