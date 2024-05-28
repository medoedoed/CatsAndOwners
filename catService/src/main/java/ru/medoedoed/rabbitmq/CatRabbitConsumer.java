package ru.medoedoed.rabbitmq;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.medoedoed.models.dataModels.CatDto;
import ru.medoedoed.services.CatService;

@Component
@RequiredArgsConstructor
@EnableRabbit
public class CatRabbitConsumer {
  private final CatService catService;

  @RabbitListener(queues = "cats.getAll")
  public List<CatDto> getAllCats() {
    return catService.getAll();
  }

  @RabbitListener(queues = "cats.getById")
  public CatDto getCatById(Long catId) {
    return catService.getById(catId);
  }

  @RabbitListener(queues = "cats.save")
  public Long saveCat(CatDto catData) {
    return catService.save(catData);
  }

  @RabbitListener(queues = "cats.update")
  public void updateCat(CatDto catData) {
    catService.update(catData);
  }

  @RabbitListener(queues = "cats.delete")
  public void deleteCatById(Long id) {
    catService.delete(id);
  }
}
