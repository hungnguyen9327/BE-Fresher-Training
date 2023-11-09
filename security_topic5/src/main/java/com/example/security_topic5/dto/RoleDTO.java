package com.example.security_topic5.dto;

import jakarta.annotation.Nullable;
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
public class RoleDTO {

  @Pattern(regexp = "^ROLE_[A-Z]{2,}$", message = "Invalid Role Name: Must be pattern 'ROLE_<role name>' (all uppercase)")
  private String name;

  @Nullable
  private Instant createdAt;

  @Nullable
  private Instant updatedAt;

  private Set<PermissionDTO> permissions = new HashSet<>();
}
