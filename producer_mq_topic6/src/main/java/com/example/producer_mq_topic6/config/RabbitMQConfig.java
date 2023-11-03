package com.example.producer_mq_topic6.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

@Configuration
public class RabbitMQConfig {
  @Value("${rabbitmq.queue.name}")
  private String queue;

  @Value("${rabbitmq.exchange.name}")
  private String exchange;

  @Value("${rabbitmq.routing.key}")
  private String routingKey;

  @Bean
  public Queue queue() {
    return new Queue(queue);
  }

  // spring bean for rabbitmq exchange
  @Bean
  public TopicExchange exchange() {
    return new TopicExchange(exchange);
  }

  // binding between queue and exchange using routing key
  @Bean
  public Binding binding() {
    return BindingBuilder
        .bind(queue())
        .to(exchange())
        .with(routingKey);
  }

  @Bean
  public MappingJackson2MessageConverter jackson2Converter() {
    return new MappingJackson2MessageConverter();
  }
}
