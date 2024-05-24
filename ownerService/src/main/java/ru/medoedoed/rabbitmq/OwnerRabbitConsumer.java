package ru.medoedoed.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.medoedoed.models.dataModels.OwnerDto;
import ru.medoedoed.services.OwnerService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OwnerRabbitConsumer {
  private final OwnerService ownerService;

  @RabbitListener(queues = "owners.getAll")
  public List<OwnerDto> getAll() {
    return ownerService.getAll();
  }

  @RabbitListener(queues = "owners.getById")
  public OwnerDto getOwnerById(Long catId) {
    return ownerService.getById(catId);
  }

  @RabbitListener(queues = "owners.save")
  public Long saveOwner(OwnerDto catData) {
    return ownerService.save(catData);
  }

  @RabbitListener(queues = "owners.update")
  public void updateOwner(OwnerDto catData) {
    ownerService.update(catData);
  }

  @RabbitListener(queues = "owners.delete")
  public void deleteOwnerById(Long id) {
    ownerService.delete(id);
  }
}
