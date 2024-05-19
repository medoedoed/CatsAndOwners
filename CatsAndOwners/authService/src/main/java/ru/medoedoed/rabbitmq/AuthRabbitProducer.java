package ru.medoedoed.rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.medoedoed.models.dataModels.OwnerDto;

@Service
@RequiredArgsConstructor
public class AuthRabbitProducer {
  private final RabbitTemplate rabbitTemplate;


  public void createQueue(String queueName) {
    try {
      rabbitTemplate.execute(channel -> {
        channel.queueDeclare(queueName, true, false, false, null);
        return null;
      });
    } catch (AmqpException e) {
      // Обработка ошибки
      e.printStackTrace();
    }
  }

  public void saveOwner(OwnerDto ownerData) {
    String queueName = "owner.save";
    try {
      createQueue(queueName);
    } catch (AmqpException e) {
      if (!e.getMessage().contains("in use")) {
        throw e;
      }
    }
    rabbitTemplate.convertAndSend(queueName, ownerData);
  }
}
