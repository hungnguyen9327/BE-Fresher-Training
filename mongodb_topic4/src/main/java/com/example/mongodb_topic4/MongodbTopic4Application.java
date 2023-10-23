package com.example.mongodb_topic4;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.OffsetDateTime;
import java.util.Optional;

@SpringBootApplication
@EnableMongoAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
public class MongodbTopic4Application {
    
    public static void main(String[] args) {
        SpringApplication.run(MongodbTopic4Application.class, args);
    }

}
