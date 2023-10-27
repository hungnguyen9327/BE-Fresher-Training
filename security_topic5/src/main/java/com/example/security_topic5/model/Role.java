package com.example.security_topic5.model;

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
@Document(collection = "roles")
public class Role {

  @Id
  private String _id;
  private String name;
  private Instant createdAt;
  private Instant updatedAt;

  @DBRef
  private Set<Permission> permissions = new HashSet<>();
}
