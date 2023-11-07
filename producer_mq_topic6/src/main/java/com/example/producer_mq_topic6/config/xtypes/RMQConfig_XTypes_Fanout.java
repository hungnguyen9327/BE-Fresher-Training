package com.example.producer_mq_topic6.config.xtypes;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

public class RMQConfig_XTypes_Fanout {
  private static final String EXCHANGE_NAME = "fanout.exchange";
  private static final String QUEUE_NAME = "fanout.queue";
  @Bean
  FanoutExchange fanoutExchange() {
    return new FanoutExchange(EXCHANGE_NAME);
  }

  @Bean
  Queue queue1() {
    return new Queue(QUEUE_NAME + ".queue1");
  }

  @Bean
  Queue queue2() {
    return new Queue(QUEUE_NAME + ".queue2");
  }

  @Bean
  Binding binding1(FanoutExchange fanoutExchange, Queue queue1) {
    return BindingBuilder.bind(queue1).to(fanoutExchange);
  }

  @Bean
  Binding binding2(FanoutExchange fanoutExchange, Queue queue2) {
    return BindingBuilder.bind(queue2).to(fanoutExchange);
  }
}
