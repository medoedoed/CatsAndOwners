package ru.medoedoed.rabbitMQ;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMqService {
  private final RabbitMqConsumer rabbitMqConsumer;

  public void processMessage(String message) {
  }
}
