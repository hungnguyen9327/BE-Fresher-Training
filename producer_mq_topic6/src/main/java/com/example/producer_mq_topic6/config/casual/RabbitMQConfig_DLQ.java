package com.example.producer_mq_topic6.config.casual;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class RabbitMQConfig_DLQ {
  //Main
  @Value("${rabbitmq.queue.main-queue}")
  private String mainQueue;
  @Value("${rabbitmq.exchange.main-exchange}")
  private String mainExchange;
  @Value("${rabbitmq.routing.main-key}")
  private String mainRoutingKey;

  //DL
  @Value("${rabbitmq.queue.dead-letter-queue}")
  private String deadLetterQueue;
  @Value("${rabbitmq.exchange.dead-letter-exchange}")
  private String deadLetterExchange;
  @Value("${rabbitmq.routing.dead-letter-key}")
  private String deadLetterRoutingKey;

  //Exchange
  @Bean
  DirectExchange mainExchange() {
    return new DirectExchange(mainExchange);
  }
  @Bean
  DirectExchange deadLetterExchange() {
    return new DirectExchange(deadLetterExchange);
  }

  //Queue
  @Bean
  Queue mainQueue() {
    return QueueBuilder.durable(mainQueue)
        .withArgument("x-dead-letter-exchange", deadLetterExchange)
        .withArgument("x-dead-letter-routing-key", deadLetterRoutingKey)
        .quorum()
        .build();
  }
  @Bean
  Queue deadLetterQueue() {
    return QueueBuilder.durable(deadLetterQueue).build();
  }

  @Bean
  Binding binding() {
    return BindingBuilder
        .bind(mainQueue())
        .to(mainExchange())
        .with(mainRoutingKey);
  }
  @Bean
  Binding DLQbinding() {
    return BindingBuilder
        .bind(deadLetterQueue())
        .to(deadLetterExchange())
        .with(deadLetterRoutingKey);
  }
}
