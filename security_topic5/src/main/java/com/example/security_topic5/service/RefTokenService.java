package com.example.security_topic5.service;

import com.example.security_topic5.dto.UserDTO;
import com.example.security_topic5.model.RefreshToken;
import com.example.security_topic5.model.User;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface RefTokenService<T> {

  Optional<T> getByToken(String token);

  T getByUser(UserDTO user);

  T createRefreshToken(UserDTO user);

  void deleteByUserId(String userId);

  RefreshToken verifyExpiration(RefreshToken token);
}
