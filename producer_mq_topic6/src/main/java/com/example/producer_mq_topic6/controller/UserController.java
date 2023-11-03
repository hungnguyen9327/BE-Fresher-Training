package com.example.producer_mq_topic6.controller;

import com.example.producer_mq_topic6.model.User;
import com.example.producer_mq_topic6.service.UserService;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
  private final UserService userService;

  @PostMapping(path = { "/publish" })
  public ResponseEntity<?> publishProductMessage(@RequestBody User user) {
    CompletableFuture<Void> future = userService.publishUser(user);
    future.join();
    return new ResponseEntity<>("User Message Published", HttpStatus.OK);
  }
}
