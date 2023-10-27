package com.example.security_topic5.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RefTokenResponse {

  private String accessToken;
  private String refreshToken;
}
