package ru.medoedoed.rabbitmq;

import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.medoedoed.models.dataModels.CatColorDto;
import ru.medoedoed.models.dataModels.OwnerDto;

@Service
@RequiredArgsConstructor
public class CatRabbitProducer {
  private final RabbitTemplate rabbitTemplate;

  public Long getCurrentUser() {
    return (Long) rabbitTemplate.convertSendAndReceive("user.requestCurrentUser");
  }

  public CatColorDto getColorById(Long id) {
    return (CatColorDto) rabbitTemplate.convertSendAndReceive("color.requestById", id);
  }

  public OwnerDto getOwnerById(Long id) {
    return (OwnerDto) rabbitTemplate.convertSendAndReceive("owner.requestById", id);
  }
}
