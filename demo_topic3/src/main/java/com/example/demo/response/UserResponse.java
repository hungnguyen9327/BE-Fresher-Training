package com.example.demo.response;

import com.example.demo.model.Role;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

  private UUID id;
  private String username;
  private String email;
  private int status;
  private Timestamp createdAt;
  private Timestamp updatedAt;
  private Set<Role> roles;
}
