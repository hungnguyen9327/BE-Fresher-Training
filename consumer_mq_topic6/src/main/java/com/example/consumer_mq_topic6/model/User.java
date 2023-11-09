package com.example.consumer_mq_topic6.model;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.redis.core.RedisHash;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("users")
//@Document(collection = "users")
public class User {
  @Id
  private String _id;
  private String username;
  private String email;
  private String status;

  @Field("message_received")
  private boolean messageReceived;

  @Field("message_count")
  private int messageCount;

  private Instant createdAt;
  private Instant updatedAt;
}
