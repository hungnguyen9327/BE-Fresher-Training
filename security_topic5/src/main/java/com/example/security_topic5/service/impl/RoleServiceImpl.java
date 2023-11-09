package com.example.security_topic5.service.impl;

import com.example.security_topic5.config.AsyncConfig;
import com.example.security_topic5.dto.RoleDTO;
import com.example.security_topic5.exception.AccessingDataException;
import com.example.security_topic5.exception.NotFoundException;
import com.example.security_topic5.model.Permission;
import com.example.security_topic5.model.Role;
import com.example.security_topic5.repo.PermissionRepo;
import com.example.security_topic5.repo.RoleRepo;
import com.example.security_topic5.service.AuthorizationService;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Qualifier("roleService")
public class RoleServiceImpl implements AuthorizationService<RoleDTO> {

  ModelMapper modelMapper;
  RoleRepo repo;
  PermissionRepo perRepo;

  @Override
  @Async(AsyncConfig.TASK_EXECUTOR_SERVICE)
  public CompletableFuture<RoleDTO> getById(String id) {
    try {
      Optional<Role> optional = repo.findById(id);
      Role role = optional
          .orElseThrow(() -> new NotFoundException("Permission with " + id + " id not found"));
      return CompletableFuture.completedFuture(modelMapper.map(role, RoleDTO.class));
    } catch (DataAccessException e) {
      throw new AccessingDataException("Try to access data from db failed");
    }
  }

  @Override
  @Async(AsyncConfig.TASK_EXECUTOR_SERVICE)
  public CompletableFuture<RoleDTO> getByName(String name) {
    try {
      Optional<Role> optional = repo.findByName(name)
          .thenApply(res -> res).get();
      Role role = optional
          .orElseThrow(() -> new NotFoundException("Permission with " + name + " name not found"));
      return CompletableFuture.completedFuture(modelMapper.map(role, RoleDTO.class));
    } catch (DataAccessException e) {
      throw new AccessingDataException("Try to access data from db failed");
    } catch (ExecutionException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  @Async(AsyncConfig.TASK_EXECUTOR_SERVICE)
  public CompletableFuture<List<RoleDTO>> getAll() {
    try {
      List<RoleDTO> list = repo.findAll().stream()
          .map(role -> modelMapper.map(role, RoleDTO.class))
          .toList();

      return CompletableFuture.completedFuture(list);
    } catch (DataAccessException e) {
      throw new AccessingDataException("Try to access data from db failed");
    }
  }

  @Override
  @Async(AsyncConfig.TASK_EXECUTOR_SERVICE)
  public CompletableFuture<Page<RoleDTO>> findAllByPage(Pageable pageable) {
    try {
      Page<RoleDTO> pageList = repo.findAll(pageable)
          .map(role -> modelMapper.map(role, RoleDTO.class));
      return CompletableFuture.completedFuture(pageList);
    } catch (DataAccessException e) {
      throw new AccessingDataException("Try to access data from db failed");
    }
  }

  @Override
  @Async(AsyncConfig.TASK_EXECUTOR_SERVICE)
  public CompletableFuture<RoleDTO> create(RoleDTO data) {
    Instant curTime = (new Timestamp(System.currentTimeMillis()).toInstant());
    try {
      Role role = modelMapper.map(data, Role.class);
      Set<Permission> permissions = data.getPermissions().stream()
          .map(perReq -> {
            try {
              return perRepo.findByName(perReq.getName())
                  .orElseThrow(() -> new NotFoundException(
                      "Permission with " + perReq.getName() + " name not found"));
            } catch (Exception e) {
              throw new RuntimeException(e);
            }
          })
          .collect(Collectors.toSet());

      role.setPermissions(permissions);
      role.setCreatedAt(curTime);
      role.setUpdatedAt(curTime);

      return CompletableFuture.completedFuture(modelMapper.map(repo.insert(role), RoleDTO.class));
    } catch (DataAccessException e) {
      throw new AccessingDataException("Try to access data from db failed");
    }
  }

  @Override
  @Async(AsyncConfig.TASK_EXECUTOR_SERVICE)
  public CompletableFuture<RoleDTO> update(String id, RoleDTO data) {
    Instant curTime = (new Timestamp(System.currentTimeMillis()).toInstant());
    try {
      Role updateInfo = modelMapper.map(data, Role.class);
      Set<Permission> permissions = data.getPermissions().stream()
          .map(perReq -> {
            try {
              return perRepo.findByName(perReq.getName())
                  .orElseThrow(() -> new NotFoundException(
                      "Permission with " + perReq.getName() + " name not found"));
            } catch (Exception e) {
              throw new RuntimeException(e);
            }
          })
          .collect(Collectors.toSet());

      Role updateRole = repo.findById(id)
          .orElseThrow(() -> new NotFoundException("Role with " + id + " id not found"));
      updateRole.setName(updateInfo.getName());
      updateRole.setPermissions(permissions);
      updateRole.setUpdatedAt(curTime);
      return CompletableFuture.completedFuture(
          modelMapper.map(repo.save(updateRole), RoleDTO.class));
    } catch (DataAccessException e) {
      throw new AccessingDataException("Try to access data from db failed");
    }
  }

  @Override
  @Async(AsyncConfig.TASK_EXECUTOR_SERVICE)
  public CompletableFuture<Void> delete(String id) {
    return CompletableFuture.runAsync(() -> {
      repo.deleteById(id);
    });
  }
}
