package com.example.rcluster.service;

import com.example.rcluster.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RedisService {

  private final RedisTemplate<String, Object> redisTemplate;

  public RedisService(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public User cacheUser(User user) {
//    redisTemplate.opsForValue().set(user.getName(), user, Duration.ofMinutes(2L));
    return null;
  }

  public User getCachedUserByName(String name) {
    var user = redisTemplate.opsForValue().get(name);
    return (User) user;
  }
}
