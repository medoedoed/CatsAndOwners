package ru.medoedoed.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
  @Bean
  public ConnectionFactory connectionFactory() {
    var factory = new CachingConnectionFactory("localhost");
    factory.setUsername("guest");
    factory.setPassword("guest");
    return factory;
  }

  @Bean
  public MessageConverter jsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public RabbitAdmin amqpAdmin() {
    return new RabbitAdmin(connectionFactory());
  }

  @Bean
  public RabbitTemplate rabbitTemplate() {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
    rabbitTemplate.setMessageConverter(jsonMessageConverter());
    return rabbitTemplate;
  }

  @Bean
  public Queue catsGetAllQueue() {
    return new Queue("cats.getAll");
  }

  @Bean
  public Queue catsGetByIdQueue() {
    return new Queue("cats.getById");
  }

  @Bean
  public Queue catsSaveQueue() {
    return new Queue("cats.save");
  }

  @Bean
  public Queue catsDeleteQueue() {
    return new Queue("cats.delete");
  }

  @Bean
  public Queue catsUpdateQueue() {
    return new Queue("cats.update");
  }
}
