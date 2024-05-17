package ru.medoedoed.rabbitMQ;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqConsumer {
  @RabbitListener(queues = "queue")
  public void receiveMessage(String message) {
    // Обработка полученного сообщения
  }
}
