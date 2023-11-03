package com.example.producer_mq_topic6.service.impl;

import com.example.producer_mq_topic6.model.User;
import com.example.producer_mq_topic6.service.UserService;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  @Value("${rabbitmq.exchange.name}")
  private String exchange;

  @Value("${rabbitmq.routing.key}")
  private String routingKey;

  private final RabbitMessagingTemplate rabbitMessagingTemplate;
  private final MappingJackson2MessageConverter mappingJackson2MessageConverter;

  @Override
  public CompletableFuture<Void> publishUser(User user) {

    return CompletableFuture.supplyAsync(() -> {
      rabbitMessagingTemplate.setMessageConverter(mappingJackson2MessageConverter);
      rabbitMessagingTemplate.convertAndSend(exchange, routingKey, user);
      return null;
    });
  }
}
