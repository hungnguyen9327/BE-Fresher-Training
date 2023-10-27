package com.example.security_topic5.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = ApplicationProperties.CONFIGURATION_PROPERTY_PREFIX,
    ignoreUnknownFields = false)
@Component
public class ApplicationProperties {

  static final String CONFIGURATION_PROPERTY_PREFIX = "application";
  private final Async async = new Async();

  public Async getAsync() {
    return async;
  }

  @Getter
  @Setter
  public static class Async {

    private Integer corePoolSize;
    private Integer maxPoolSize;
    private Integer queueCapacity;
  }
}
