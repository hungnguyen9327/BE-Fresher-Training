package com.example.producer_mq_topic6.config.xtypes;

import java.util.HashMap;
import java.util.Map;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

public class RMQConfig_XTypes_Header {
  private static final String EXCHANGE_NAME = "header.exchange";
  private static final String QUEUE_NAME = "header.queue";

  @Bean
  public Queue queue() {
    return new Queue("header.queue");
  }

  @Bean
  public HeadersExchange headersExchange() {
    return new HeadersExchange("headers.exchange");
  }

  @Bean
  public Binding binding(Queue queue, HeadersExchange headersExchange) {
    Map<String, Object> headerValues = new HashMap<>();
    headerValues.put("header-key-1", "value-1");
    headerValues.put("header-key-2", "value-2");

    return BindingBuilder
        .bind(queue)
        .to(headersExchange)
        .whereAll(headerValues).match();
    //.where("header-key").matches("header-value");
  }
//  MessageProperties properties = new MessageProperties();
//        properties.setHeader("header-key", "header-value");
//  Message msg = new Message(message.getBytes(), properties);
//        rabbitTemplate.send("headers.exchange", "", msg);
//MessageProperties properties = new MessageProperties();
//        properties.setHeader("header-key-1", "value-1");
//        properties.setHeader("header-key-2", "value-2");
//  Message msg = new Message(message.getBytes(), properties);
//        rabbitTemplate.send("multi-headers.exchange", "", msg);
}
