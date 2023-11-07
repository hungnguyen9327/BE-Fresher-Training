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

public class RabbitMQConfig_Retry {
  //Queue
  @Value("${rabbitmq.queue.main-queue}")
  private String mainQueue;
  @Value("${rabbitmq.queue.retry-queue}")
  private String retryQueue;
  @Value("${rabbitmq.queue.dead-letter-queue}")
  private String deadLetterQueue;

  //Routing key
  @Value("${rabbitmq.routing.main-key}")
  private String mainRoutingKey;

  //Delay time in retry
  @Value("${rabbitmq.retry.delay-in-ms}")
  private Integer retryDelayTime;

  //Exchange
  @Value("${rabbitmq.exchange.main-exchange}")
  private String mainExchange;

  @Bean
  DirectExchange mainExchange() {
    return new DirectExchange(mainExchange);
  }

  @Bean
  Queue mainQueue() {
    return QueueBuilder.durable(mainQueue)
        .deadLetterExchange(mainExchange)
        .deadLetterRoutingKey(retryQueue)
        .quorum()
        .build();
  }
  @Bean
  Queue retryQ() {
    return QueueBuilder.durable(retryQueue)
        .deadLetterExchange(mainExchange)
        .deadLetterRoutingKey(mainRoutingKey)
        .ttl(retryDelayTime)
        .quorum()
        .build();
  }
  @Bean
  Queue undeliveredQueue() {
    return QueueBuilder.durable(deadLetterQueue)
        .quorum()
        .build();
  }

  @Bean
  Binding mainBinding(Queue mainQueue, DirectExchange mainExchange) {
    return BindingBuilder
        .bind(mainQueue)
        .to(mainExchange)
        .with(mainRoutingKey);
  }
  @Bean
  Binding retryBinding(Queue retryQ, DirectExchange mainExchange) {
    return BindingBuilder
        .bind(retryQ)
        .to(mainExchange)
        .with(retryQueue);
  }
  @Bean
  Binding undeliveredBinding(Queue undeliveredQueue, DirectExchange mainExchange) {
    return BindingBuilder
        .bind(undeliveredQueue)
        .to(mainExchange)
        .with(deadLetterQueue);
  }
}
