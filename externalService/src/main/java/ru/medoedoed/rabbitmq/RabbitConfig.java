package ru.medoedoed.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
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
    return new CachingConnectionFactory("localhost");
  }

  @Bean
  public MessageConverter jsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public AmqpAdmin amqpAdmin() {
    return new RabbitAdmin(connectionFactory());
  }

  @Bean
  public RabbitTemplate rabbitTemplate() {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
    rabbitTemplate.setMessageConverter(jsonMessageConverter());
    return rabbitTemplate;
  }

  @Bean
  public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory());
    factory.setMessageConverter(jsonMessageConverter());
    return factory;
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

  @Bean
  public Queue colorsGetAllQueue() {
    return new Queue("colors.getAll");
  }

  @Bean
  public Queue colorsGetByIdQueue() {
    return new Queue("colors.getById");
  }

  @Bean
  public Queue colorsSaveQueue() {
    return new Queue("colors.save");
  }

  @Bean
  public Queue colorsDeleteQueue() {
    return new Queue("colors.delete");
  }

  @Bean
  public Queue colorsUpdateQueue() {
    return new Queue("colors.update");
  }

  @Bean
  public Queue ownerGetByIdQueue() {
    return new Queue("owner.getById");
  }

  @Bean
  public Queue ownerGetAllQueue() {
    return new Queue("owner.getAll");
  }

  @Bean
  public Queue ownerSaveQueue() {
    return new Queue("owner.save");
  }

  @Bean
  public Queue ownerDeleteQueue() {
    return new Queue("owner.delete");
  }

  @Bean
  public Queue ownerUpdateQueue() {
    return new Queue("owner.update");
  }
}
