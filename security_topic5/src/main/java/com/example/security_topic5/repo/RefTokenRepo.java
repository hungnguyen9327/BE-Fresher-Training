package com.example.security_topic5.repo;

import com.example.security_topic5.config.AsyncConfig;
import com.example.security_topic5.model.RefreshToken;
import com.example.security_topic5.model.User;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
public interface RefTokenRepo extends MongoRepository<RefreshToken, String> {

  @Async(AsyncConfig.TASK_EXECUTOR_REPOSITORY)
  CompletableFuture<Optional<RefreshToken>> findByToken(String token);

  @Async(AsyncConfig.TASK_EXECUTOR_REPOSITORY)
  CompletableFuture<Optional<RefreshToken>> findByUser(User user);

  @Query(value = "{ 'user._id': :#{#userId} }", delete = true)
  void deleteByUserId(String userId);
}
