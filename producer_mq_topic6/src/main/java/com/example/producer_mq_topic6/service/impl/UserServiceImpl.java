package com.example.producer_mq_topic6.service.impl;

import com.example.producer_mq_topic6.model.User;
import com.example.producer_mq_topic6.service.UserService;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  @Value("${rabbitmq.exchange.main-exchange}")
  private String exchange;

  @Value("${rabbitmq.routing.main-key}")
  private String routingKey;

  private final AmqpTemplate amqpTemplate;

  @Override
  public CompletableFuture<Void> publishUser(User user) {

    return CompletableFuture.supplyAsync(() -> {
      amqpTemplate.convertAndSend(exchange, routingKey, user
          // Config for delay queue (and priority queue)
//          message -> {
//            message.getMessageProperties().setHeader("x-delay", 5000); // delay
//            message.getMessageProperties().setHeader("x-priority", 5); // priority
//            return message;
//          }
      );
      return null;
    });
  }
}
