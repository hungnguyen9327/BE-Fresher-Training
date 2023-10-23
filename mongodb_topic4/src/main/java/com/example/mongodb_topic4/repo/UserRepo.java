package com.example.mongodb_topic4.repo;

import com.example.mongodb_topic4.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends MongoRepository<User, String> {
    User findByUsername(String username);

    List<User> findByEmailLike(String email);
}
