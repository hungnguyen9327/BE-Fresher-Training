package com.example.restful_topic4.dto;

import com.example.restful_topic4.enums.UserStatus;
import com.example.restful_topic4.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

  private UUID id;

  private String username;

  private String email;
  
  private UserStatus status;

  private Timestamp createdAt;

  private Timestamp updatedAt;

  private Set<Role> roles = new HashSet<>();
}
