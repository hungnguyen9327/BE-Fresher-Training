package com.example.security_topic5.repo;

import com.example.security_topic5.config.AsyncConfig;
import com.example.security_topic5.model.Role;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends MongoRepository<Role, String> {

  @Async(AsyncConfig.TASK_EXECUTOR_REPOSITORY)
  CompletableFuture<Optional<Role>> findByName(String name);
}
