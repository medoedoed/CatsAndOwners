package ru.medoedoed.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthRabbitProducer {
  private final AuthRabbitConsumer authRabbitConsumer;

  public void processMessage(String message) {
  }
}
