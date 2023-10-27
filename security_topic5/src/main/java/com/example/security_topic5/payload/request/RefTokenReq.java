package com.example.security_topic5.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefTokenReq {

  @NotBlank(message = "Invalid Token: Empty")
  @NotNull(message = "Invalid Token: Refresh Token is NULL")
  private String refreshToken;
}
