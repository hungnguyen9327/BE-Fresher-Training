package com.example.security_topic5.model;

import com.example.security_topic5.enums.demo.ModelStatus;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

  @Id
  private String _id;
  private String username;
  private String email;
  private String password;
  private ModelStatus status;
  private Instant createdAt;
  private Instant updatedAt;

  @DBRef
  private Set<Role> roles = new HashSet<>();
}
