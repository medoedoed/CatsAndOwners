package ru.medoedoed.rabbitmq;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.medoedoed.models.dataModels.CatColorDto;
import ru.medoedoed.services.ColorService;

@Component
@RequiredArgsConstructor
@EnableRabbit
public class ColorRabbitConsumer {
  private final ColorService colorService;

  @RabbitListener(queues = "colors.getAll")
  public List<CatColorDto> getAll() {
    return colorService.getAll();
  }

  @RabbitListener(queues = "colors.getById")
  public CatColorDto getColorById(Long catId) {
    return colorService.getById(catId);
  }

  @RabbitListener(queues = "colors.save")
  public Long saveColor(CatColorDto catData) {
    return colorService.save(catData);
  }

  @RabbitListener(queues = "colors.update")
  public void updateColor(CatColorDto catData) {
    colorService.update(catData);
  }

  @RabbitListener(queues = "colors.delete")
  public void deleteColorById(Long id) {
    colorService.delete(id);
  }
}
