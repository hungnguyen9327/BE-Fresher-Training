package com.example.producer_mq_topic6.config.casual;

import java.util.HashMap;
import java.util.Map;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
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

public class RabbitMQConfig_Delay {

  @Value("${rabbitmq.queue.delay-queue}")
  private String delayQueue;
  @Value("${rabbitmq.exchange.delay-exchange}")
  private String delayExchange;
  @Value("${rabbitmq.routing.delay-key}")
  private String delayRoutingKey;

  @Bean
  CustomExchange delayExchange() {
    Map<String, Object> args = new HashMap<>();
    args.put("x-delayed-type", "direct"); //delay

//    args.put("x-max-priority", 10); //priority queue
//    args.put("x-expires", 10000); //expiry queue
//    args.put("x-message-ttl", 30000); //message ttl queue
//    args.put("x-queue-mode", "lazy"); //lazy queue (2 values is valid - "lazy" & "default")

    return new CustomExchange(delayExchange,
        "x-delayed-message", true, false, args);
  }

  @Bean
  public Queue delayQueue() {
    return QueueBuilder
        .durable(delayQueue)
        .build();
  }

  @Bean
  public Binding delayBinding(CustomExchange delayExchange, Queue delayQueue) {
    return BindingBuilder
        .bind(delayQueue)
        .to(delayExchange)
        .with(delayRoutingKey)
        .noargs();
  }
}
