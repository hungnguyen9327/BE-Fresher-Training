package com.example.producer_mq_topic6.config.xtypes;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class RMQConfig_XTypes_Topic {
  private static final String EXCHANGE_NAME = "topic.exchange";
  private static final String QUEUE_NAME = "topic.queue";

  @Bean
  Queue queue1() {
    return new Queue(QUEUE_NAME + ".queue1");
  }

  @Bean
  Queue queue2() {
    return new Queue(QUEUE_NAME + ".queue2");
  }

  @Bean
  TopicExchange exchange() {
    return new TopicExchange(EXCHANGE_NAME);
  }

  @Bean
  Binding binding1(Queue queue1, TopicExchange exchange) {
    return BindingBuilder.bind(queue1).to(exchange)
        .with("user.#"); // push msg with key started with 'user.'
  }

  @Bean
  Binding binding2(Queue queue2, TopicExchange exchange) {
    return BindingBuilder.bind(queue2).to(exchange)
        .with("user.*.test"); // push msg with key having pattern 'user.<any>.test'
  }
}
