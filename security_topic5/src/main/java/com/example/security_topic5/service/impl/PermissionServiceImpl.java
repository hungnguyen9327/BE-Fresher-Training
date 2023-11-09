package com.example.security_topic5.service.impl;

import com.example.security_topic5.config.AsyncConfig;
import com.example.security_topic5.dto.PermissionDTO;
import com.example.security_topic5.exception.AccessingDataException;
import com.example.security_topic5.exception.NotFoundException;
import com.example.security_topic5.model.Permission;
import com.example.security_topic5.repo.PermissionRepo;
import com.example.security_topic5.service.AuthorizationService;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
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
@Qualifier("permissionService")
public class PermissionServiceImpl implements AuthorizationService<PermissionDTO> {

  ModelMapper modelMapper;
  PermissionRepo repo;

  @Override
  @Async(AsyncConfig.TASK_EXECUTOR_SERVICE)
  public CompletableFuture<PermissionDTO> getById(String id) {
    try {
      Optional<Permission> optional = repo.findById(id);
      Permission permission = optional
          .orElseThrow(() -> new NotFoundException("Permission with " + id + " id not found"));
      return CompletableFuture.completedFuture(modelMapper.map(permission, PermissionDTO.class));
    } catch (DataAccessException e) {
      throw new AccessingDataException("Try to access data from db failed");
    }
  }

  @Override
  @Async(AsyncConfig.TASK_EXECUTOR_SERVICE)
  public CompletableFuture<PermissionDTO> getByName(String name) {
    try {
      Optional<Permission> optional = repo.findByName(name);
      Permission permission = optional
          .orElseThrow(() -> new NotFoundException("Permission with " + name + " name not found"));
      return CompletableFuture.completedFuture(modelMapper.map(permission, PermissionDTO.class));
    } catch (DataAccessException e) {
      throw new AccessingDataException("Try to access data from db failed");
    }
  }

  @Override
  @Async(AsyncConfig.TASK_EXECUTOR_SERVICE)
  public CompletableFuture<List<PermissionDTO>> getAll() {
    try {
      List<PermissionDTO> list = repo.findAll().stream()
          .map(permission -> modelMapper.map(permission, PermissionDTO.class))
          .toList();
      return CompletableFuture.completedFuture(list);
    } catch (DataAccessException e) {
      throw new AccessingDataException("Try to access data from db failed");
    }
  }

  @Override
  @Async(AsyncConfig.TASK_EXECUTOR_SERVICE)
  public CompletableFuture<Page<PermissionDTO>> findAllByPage(Pageable pageable) {
    try {
      Page<PermissionDTO> pageList = repo.findAll(pageable)
          .map(permission -> modelMapper.map(permission, PermissionDTO.class));
      return CompletableFuture.completedFuture(pageList);
    } catch (DataAccessException e) {
      throw new AccessingDataException("Try to access data from db failed");
    }
  }

  @Override
  @Async(AsyncConfig.TASK_EXECUTOR_SERVICE)
  public CompletableFuture<PermissionDTO> create(PermissionDTO data) {
    Instant curTime = (new Timestamp(System.currentTimeMillis()).toInstant());
    try {
      Permission permission = modelMapper.map(data, Permission.class);
      permission.setCreatedAt(curTime);
      permission.setUpdatedAt(curTime);
      return CompletableFuture.completedFuture(
          modelMapper.map(repo.insert(permission), PermissionDTO.class));
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  @Async(AsyncConfig.TASK_EXECUTOR_SERVICE)
  public CompletableFuture<PermissionDTO> update(String id, PermissionDTO data) {
    Instant curTime = (new Timestamp(System.currentTimeMillis()).toInstant());
    try {
      Permission updateInfo = modelMapper.map(data, Permission.class);
      Permission updatePermission = repo.findById(id)
          .orElseThrow(() -> new NotFoundException("Permission with " + id + " id not found"));
      updatePermission.setName(updateInfo.getName());
      updatePermission.setUpdatedAt(curTime);
      return CompletableFuture.completedFuture(
          modelMapper.map(repo.save(updatePermission), PermissionDTO.class));
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
