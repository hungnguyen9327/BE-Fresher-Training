package com.example.consumer_mq_topic6.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface UserService<T> {
  List<T> getAll();
  T getById(String id);
  T update(String id, T data);
  void deleteById(String id);
}
