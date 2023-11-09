package com.example.consumer_mq_topic6.config;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
@EnableRabbit
public class RabbitMQConfig implements RabbitListenerConfigurer {
  @Bean
  public MappingJackson2MessageConverter jackson2Converter() {
    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
    return converter;
  }

  @Bean
  public DefaultMessageHandlerMethodFactory myHandlerMethodFactory() {
    DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
    factory.setMessageConverter(jackson2Converter());
    return factory;
  }

  @Override
  public void configureRabbitListeners(RabbitListenerEndpointRegistrar listenerRegistrar) {
    listenerRegistrar.setMessageHandlerMethodFactory(myHandlerMethodFactory());
  }
}
