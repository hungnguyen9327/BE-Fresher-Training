package com.example.security_topic5.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilsConfig {

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
