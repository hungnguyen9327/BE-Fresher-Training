package com.example.demo.repo;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {

  Optional<User> findByUsername(String username);

  Optional<User> findByUsernameOrEmail(String username, String email);

  Boolean existsByEmail(String email);

  Boolean existsByUsername(String username);
}
