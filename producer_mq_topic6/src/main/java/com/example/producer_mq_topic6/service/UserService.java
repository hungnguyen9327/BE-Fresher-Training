package com.example.producer_mq_topic6.service;

import com.example.producer_mq_topic6.model.User;
import java.util.concurrent.CompletableFuture;

public interface UserService {
  CompletableFuture<Void> publishUser(User user);
}
