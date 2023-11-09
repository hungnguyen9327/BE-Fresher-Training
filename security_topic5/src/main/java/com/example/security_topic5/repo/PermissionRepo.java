package com.example.security_topic5.repo;

import com.example.security_topic5.model.Permission;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepo extends MongoRepository<Permission, String> {

  Optional<Permission> findByName(String name);
  
}
