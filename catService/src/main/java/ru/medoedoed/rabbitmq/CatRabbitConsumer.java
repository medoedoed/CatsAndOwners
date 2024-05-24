package ru.medoedoed.rabbitmq;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CatRabbitConsumer {
  @RabbitListener(queues = "queue")
  public void receiveMessage(String message) {
    // Обработка полученного сообщения
  }
}