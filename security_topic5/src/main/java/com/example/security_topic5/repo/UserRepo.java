package com.example.security_topic5.repo;

import com.example.security_topic5.config.AsyncConfig;
import com.example.security_topic5.model.User;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User, String> {

  @Query("{'$or':[ {'username': :#{#username} }, { 'email': :#{#username} } ] }")
  @Async(AsyncConfig.TASK_EXECUTOR_REPOSITORY)
  CompletableFuture<Optional<User>> findByUsernameOrEmail(String username);

  @Async(AsyncConfig.TASK_EXECUTOR_REPOSITORY)
  CompletableFuture<Boolean> existsByUsername(String username);

  @Async(AsyncConfig.TASK_EXECUTOR_REPOSITORY)
  CompletableFuture<Boolean> existsByEmail(String email);
}
