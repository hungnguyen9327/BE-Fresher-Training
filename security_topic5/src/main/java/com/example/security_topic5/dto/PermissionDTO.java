package com.example.security_topic5.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Pattern;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionDTO {

  @Pattern(regexp = "^[A-Z]{2,}_[A-Z]{2,}$", message = "Invalid Permission Name (all uppercase)")
  private String name;

  @Nullable
  private Instant createdAt;

  @Nullable
  private Instant updatedAt;
}
