package com.example.pubsubredis.controller;

import com.example.pubsubredis.service.queue.MessagePublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/redis")
public class RedisController {

  private MessagePublisher publisher;

  public RedisController(MessagePublisher publisher) {
    this.publisher = publisher;
  }

  @PostMapping()
  public ResponseEntity<HttpStatus> publish(@RequestBody String message) {
    publisher.publish(message);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
