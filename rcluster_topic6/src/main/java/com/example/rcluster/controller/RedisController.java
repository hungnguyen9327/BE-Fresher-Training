package com.example.rcluster.controller;

import com.example.rcluster.model.User;
import com.example.rcluster.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class RedisController {

  private final RedisService redisService;

  @PostMapping(path = {"/", ""})
  public User cacheUser(@RequestBody User user) {
    return redisService.cacheUser(user);
  }

  @GetMapping(path = "/{name}")
  public User getCachedUserByName(@PathVariable String name) {
    return redisService.getCachedUserByName(name);
  }
}
