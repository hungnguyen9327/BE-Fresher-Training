package com.example.security_topic5.model;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "permissions")
public class Permission {

  @Id
  private String _id;
  private String name;
  private Instant createdAt;
  private Instant updatedAt;
}
