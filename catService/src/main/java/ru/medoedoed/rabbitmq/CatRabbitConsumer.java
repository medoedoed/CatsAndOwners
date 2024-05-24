package ru.medoedoed.rabbitmq;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.medoedoed.models.dataModels.CatDto;
import ru.medoedoed.services.CatService;

@Component
@RequiredArgsConstructor
public class CatRabbitConsumer {
  private final CatService catService;

  @RabbitListener(queues = "cats.getAll")
  public List<CatDto> getAllCats() {
    return catService.getAll();
  }


}
