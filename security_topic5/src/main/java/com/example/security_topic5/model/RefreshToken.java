package com.example.security_topic5.model;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "refresh_tokens")
public class RefreshToken {

  @Id
  private String _id;
  private String token;
  private Instant createdAt;

  @Indexed(name = "expired_index", expireAfterSeconds = 0)
  private Instant expiredAt;

  @DBRef
  private User user;
}
