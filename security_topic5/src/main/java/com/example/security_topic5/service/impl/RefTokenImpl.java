package com.example.security_topic5.service.impl;

import com.example.security_topic5.config.AsyncConfig;
import com.example.security_topic5.dto.UserDTO;
import com.example.security_topic5.exception.AccessingDataException;
import com.example.security_topic5.exception.NotFoundException;
import com.example.security_topic5.model.RefreshToken;
import com.example.security_topic5.model.User;
import com.example.security_topic5.repo.RefTokenRepo;
import com.example.security_topic5.service.RefTokenService;
import com.example.security_topic5.service.UserService;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class RefTokenImpl implements RefTokenService<RefreshToken> {

  @Value("${demo.security.jwtRefreshExpirationMs}")
  private Long refreshTokenDurationMs;

  RefTokenRepo repo;
  UserService<UserDTO> userService;

  ModelMapper modelMapper;

  public RefTokenImpl(RefTokenRepo repo, UserService<UserDTO> userService,
      ModelMapper modelMapper) {
    this.repo = repo;
    this.userService = userService;
    this.modelMapper = modelMapper;
  }

  @Override
  public Optional<RefreshToken> getByToken(String token) {
    try {
      return repo.findByToken(token)
          .thenApply(res -> res)
          .get();
    } catch (DataAccessException e) {
      throw new AccessingDataException("Try to access data from db failed");
    } catch (ExecutionException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public RefreshToken getByUser(UserDTO user) {
    try {
      return repo.findByUser(modelMapper.map(user, User.class))
          .thenApply(res -> res)
          .get()
          .orElseThrow(
              () -> new NotFoundException("Refresh Token of " + user.getUsername() + " not found"));
    } catch (DataAccessException e) {
      throw new AccessingDataException("Try to access data from db failed");
    } catch (ExecutionException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public RefreshToken createRefreshToken(UserDTO user) {
    RefreshToken refToken = new RefreshToken();

    refToken.setUser(modelMapper.map(user, User.class));
    refToken.setExpiredAt(Instant.now().plusMillis(refreshTokenDurationMs));
    refToken.setToken(UUID.randomUUID().toString());

    return repo.save(refToken);
  }

  @Override
  public void deleteByUserId(String userId) {
    repo.deleteByUserId(userId);
  }

  @Override
  public RefreshToken verifyExpiration(RefreshToken token) {
    try {
      Optional<RefreshToken> t = repo.findByToken(token.getToken()).thenApply(res -> res).get();
      return t.orElse(null);
    } catch (DataAccessException e) {
      throw new AccessingDataException("Try to access data from db failed");
    } catch (ExecutionException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
