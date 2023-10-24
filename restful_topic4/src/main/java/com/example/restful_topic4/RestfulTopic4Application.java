package com.example.restful_topic4;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class RestfulTopic4Application {

  public static void main(String[] args) {
    SpringApplication.run(RestfulTopic4Application.class, args);
  }

}