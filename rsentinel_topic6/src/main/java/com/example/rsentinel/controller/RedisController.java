package com.example.rsentinel.controller;

import com.example.rsentinel.request.RedisRequest;
import com.example.rsentinel.service.RedisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/redis")
public class RedisController {

  private final RedisService redisService;

  public RedisController(RedisService redisService) {
    this.redisService = redisService;
  }

  @PostMapping(path = {"/", ""})
  public ResponseEntity<?> addRedisKeyValue(@RequestBody RedisRequest redisRequest) {
    redisService.addRedis(redisRequest);
    return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.CREATED);
  }

  @GetMapping()
  public ResponseEntity<?> getFromCache(@RequestParam(value = "key") String key) {
    String value = redisService.getValue(key);
    return new ResponseEntity<String>(value, HttpStatus.OK);
  }
}
