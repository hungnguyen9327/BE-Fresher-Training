package com.example.rsentinel.service;

import com.example.rsentinel.request.RedisRequest;
import com.example.rsentinel.util.RedisUtil;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {

  private final RedisUtil<String> redisStringUtil;

  public void addRedis(RedisRequest request) {
    redisStringUtil.putValue(request.getKey(), request.getValue());
    redisStringUtil.setExpire(request.getKey(), request.getExpireMinutes(), TimeUnit.MINUTES);
  }

  public String getValue(String key) {

    String value = redisStringUtil.getValue(key);
    return value;
  }
}
