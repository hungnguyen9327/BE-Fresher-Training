package com.example.security_topic5.service.impl;

import com.example.security_topic5.config.AsyncConfig;
import com.example.security_topic5.dto.UserDTO;
import com.example.security_topic5.enums.demo.ModelStatus;
import com.example.security_topic5.exception.AccessingDataException;
import com.example.security_topic5.exception.NotFoundException;
import com.example.security_topic5.model.Role;
import com.example.security_topic5.model.User;
import com.example.security_topic5.repo.RoleRepo;
import com.example.security_topic5.repo.UserRepo;
import com.example.security_topic5.service.UserService;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService<UserDTO> {

  ModelMapper modelMapper;
  UserRepo repo;
  RoleRepo roleRepo;
  PasswordEncoder encoder;

  @Override
  @Async(AsyncConfig.TASK_EXECUTOR_SERVICE)
  public CompletableFuture<UserDTO> getById(String id) {
    try {
      Optional<User> optional = repo.findById(id);
      User user = optional
          .orElseThrow(() -> new NotFoundException("User with " + id + " id not found"));
      return CompletableFuture.completedFuture(modelMapper.map(user, UserDTO.class));
    } catch (DataAccessException e) {
      throw new AccessingDataException("Try to access data from db failed");
    }
  }

  @Override
  @Async(AsyncConfig.TASK_EXECUTOR_SERVICE)
  public CompletableFuture<List<UserDTO>> getAll() {
    try {
      List<UserDTO> list = repo.findAll().stream()
          .map(user -> modelMapper.map(user, UserDTO.class))
          .toList();
      return CompletableFuture.completedFuture(list);
    } catch (DataAccessException e) {
      throw new AccessingDataException("Try to access data from db failed");
    }
  }

  @Override
  @Async(AsyncConfig.TASK_EXECUTOR_SERVICE)
  public CompletableFuture<Page<UserDTO>> findAllByPage(Pageable pageable) {
    try {
      Page<UserDTO> pageList = repo.findAll(pageable)
          .map(user -> modelMapper.map(user, UserDTO.class));
      return CompletableFuture.completedFuture(pageList);
    } catch (DataAccessException e) {
      throw new AccessingDataException("Try to access data from db failed");
    }
  }

  @Override
  @Async(AsyncConfig.TASK_EXECUTOR_SERVICE)
  public CompletableFuture<UserDTO> create(UserDTO data) {
    Instant curTime = (new Timestamp(System.currentTimeMillis()).toInstant());
    try {
      User user = modelMapper.map(data, User.class);

      Set<Role> defaultRole = new HashSet<>();
      defaultRole.add(
          roleRepo.findByName("ROLE_USER")
              .thenApply(res ->
                  res.orElseThrow(() -> new NotFoundException("Role with ROLE_USER name not found"))
              ).get()
      );
      Set<Role> roles = data.getRoles() == null
          ? defaultRole
          : data.getRoles().stream()
              .map(roleReq -> {
                try {
                  return roleRepo.findByName(roleReq.getName())
                      .thenApply(res ->
                          res.orElseThrow(() -> new NotFoundException(
                              "Role with " + roleReq.getName() + " name not found"))
                      ).get();
                } catch (Exception e) {
                  throw new RuntimeException(e);
                }
              })
              .collect(Collectors.toSet());

      user.setPassword(encoder.encode(user.getPassword()));
      user.setRoles(roles);
      user.setCreatedAt(curTime);
      user.setUpdatedAt(curTime);
      return CompletableFuture.completedFuture(modelMapper.map(repo.insert(user), UserDTO.class));
    } catch (DataAccessException e) {
      throw new AccessingDataException("Try to access data from db failed");
    } catch (ExecutionException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  @Async(AsyncConfig.TASK_EXECUTOR_SERVICE)
  public CompletableFuture<UserDTO> update(String id, UserDTO data) {
    Instant curTime = (new Timestamp(System.currentTimeMillis()).toInstant());
    try {
      User updateInfo = modelMapper.map(data, User.class);
      Set<Role> roles = data.getRoles().stream()
          .map(roleReq -> {
            try {
              return roleRepo.findByName(roleReq.getName())
                  .thenApply(res ->
                      res.orElseThrow(() -> new NotFoundException(
                          "Role with " + roleReq.getName() + " name not found"))
                  ).get();
            } catch (Exception e) {
              throw new RuntimeException(e);
            }
          })
          .collect(Collectors.toSet());

      User updateUser = repo.findById(id)
          .orElseThrow(() -> new NotFoundException("User with id: " + id + " not found"));
      updateUser.setRoles(roles);
      updateUser.setUsername(updateInfo.getUsername());
      updateUser.setEmail(updateInfo.getEmail());
      updateUser.setStatus(updateInfo.getStatus());
      updateUser.setPassword(encoder.encode(updateInfo.getPassword()));
      updateUser.setUpdatedAt(curTime);
      return CompletableFuture.completedFuture(
          modelMapper.map(repo.save(updateUser), UserDTO.class));
    } catch (DataAccessException e) {
      throw new AccessingDataException("Try to access data from db failed");
    }
  }

  @Override
  @Async(AsyncConfig.TASK_EXECUTOR_SERVICE)
  public CompletableFuture<Void> remove(String id) {
    return CompletableFuture.runAsync(() -> {
      Instant curTime = (new Timestamp(System.currentTimeMillis()).toInstant());
      try {
        User user = repo.findById(id)
            .orElseThrow(() -> new NotFoundException("User with id: " + id + " not found"));
        user.setStatus(ModelStatus.DELETED);
        user.setUpdatedAt(curTime);
        repo.save(user);
      } catch (DataAccessException e) {
        throw new AccessingDataException("Try to access data from db failed");
      }
    });
  }

  @Override
  @Async(AsyncConfig.TASK_EXECUTOR_SERVICE)
  public CompletableFuture<Void> delete(String id) {
    return CompletableFuture.runAsync(() -> {
      repo.deleteById(id);
    });
  }

  @Override
  @Async(AsyncConfig.TASK_EXECUTOR_SERVICE)
  public CompletableFuture<Boolean> checkExistedUsername(String username) {
    return repo.existsByUsername(username).thenApply(v -> v);
  }

  @Override
  @Async(AsyncConfig.TASK_EXECUTOR_SERVICE)
  public CompletableFuture<Boolean> checkExistedEmail(String email) {
    return repo.existsByEmail(email).thenApply(v -> v);
  }
}
