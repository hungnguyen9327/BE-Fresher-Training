package com.example.rsentinel.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedisRequest {

  private String key;
  private String value;
  private Integer expireMinutes;
}
