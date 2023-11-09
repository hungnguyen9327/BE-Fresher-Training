package com.example.security_topic5.dto;

import com.example.security_topic5.enums.demo.ModelStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

  @NotBlank(message = "Invalid Username: Empty username")
  @NotNull(message = "Invalid Username: Username is NULL")
  @Pattern(regexp = "^[a-z0-9_-]{3,15}$", message = "Invalid Username: Must be of 6 - 15 characters")
  private String username;

  @Email(message = "Invalid email")
  private String email;

  @JsonIgnore
  @NotBlank(message = "Invalid Password: Empty password")
  @NotNull(message = "Invalid Password: Password is NULL")
  @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$",
      message = "Invalid Password: At least 8 character, one lowercase, one uppercase and one special character.")
  private String password;

  private ModelStatus status;

  @Nullable
  private Instant createdAt;

  @Nullable
  private Instant updatedAt;

  private Set<RoleDTO> roles = new HashSet<>();
}
