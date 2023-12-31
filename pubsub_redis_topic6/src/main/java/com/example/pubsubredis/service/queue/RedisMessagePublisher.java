package com.example.pubsubredis.service.queue;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class RedisMessagePublisher implements MessagePublisher {

  private RedisTemplate<String, Object> redisTemplate;

  private ChannelTopic topic;

  public RedisMessagePublisher() {
  }

  public RedisMessagePublisher(RedisTemplate<String, Object> redisTemplate, ChannelTopic topic) {
    this.redisTemplate = redisTemplate;
    this.topic = topic;
  }

  @Override
  public void publish(String msg) {
    redisTemplate.convertAndSend(topic.getTopic(), msg);
  }
}
