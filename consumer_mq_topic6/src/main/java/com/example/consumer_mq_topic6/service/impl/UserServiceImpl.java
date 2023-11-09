package com.example.consumer_mq_topic6.service.impl;

import com.example.consumer_mq_topic6.model.User;
import com.example.consumer_mq_topic6.repo.UserRepo;
import com.example.consumer_mq_topic6.service.UserService;
import jakarta.annotation.Resource;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

@Service
//@CacheConfig(cacheNames = "userCache")
public class UserServiceImpl implements UserService<User> {
  private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

  private UserRepo repo;

  public UserServiceImpl(UserRepo repo) {
    this.repo = repo;
  }

  @RabbitListener(queues = {"${rabbitmq.queue.name}"})
  public void receiveMessageAndCreate(User user) {
    LOGGER.info("Message received :" + user.getUsername());
    user.setMessageReceived(true);
    user.setUpdatedAt(new Timestamp(System.currentTimeMillis()).toInstant());
    user.setCreatedAt(new Timestamp(System.currentTimeMillis()).toInstant());
    repo.save(user);

//    hashOperations.putIfAbsent(hashReference, <id>, user);

    LOGGER.info("Message processed...and saved in database");
  }

  @Override
//  @Cacheable("users")
  public List<User> getAll() {
    //return repo.findAll();
    List<User> users = new ArrayList<>();
    repo.findAll().forEach(users::add);
    return users;
  }

  @Override
//  @Cacheable(cacheNames = "users", key = "#id")
  public User getById(String id) {
    return repo.findById(id).orElseThrow(() -> new RuntimeException("..."));
  }

  @Override
//  @CachePut(cacheNames = "users", key = "#id")
  public User update(String id, User data) {
    User user = repo.findById(id).orElseThrow(() -> new RuntimeException("..."));
    user.setEmail(data.getEmail());
    user.setStatus(data.getStatus());
    user.setUsername(data.getUsername());
    user.setUpdatedAt(new Timestamp(System.currentTimeMillis()).toInstant());
    return repo.save(user);
  }

  @Override
//  @CacheEvict(cacheNames = "users", key = "#id")
  public void deleteById(String id) {
    repo.deleteById(id);
  }
}
