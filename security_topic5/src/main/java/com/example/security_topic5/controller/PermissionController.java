package com.example.security_topic5.controller;

import com.example.security_topic5.config.AsyncConfig;
import com.example.security_topic5.dto.PermissionDTO;
import com.example.security_topic5.dto.RoleDTO;
import com.example.security_topic5.service.AuthorizationService;
import jakarta.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/permissions")
public class PermissionController {

  @Qualifier("permissionService")
  AuthorizationService<PermissionDTO> permissionService;

  @GetMapping(path = {"", "/"})
  @Async(AsyncConfig.TASK_EXECUTOR_CONTROLLER)
  public CompletableFuture<ResponseEntity<List<PermissionDTO>>> getAllPermissions() {
    return permissionService
        .getAll()
        .thenApply(ResponseEntity::ok)
        .exceptionally(t -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
  }

  @GetMapping("/{id}")
  @Async(AsyncConfig.TASK_EXECUTOR_CONTROLLER)
  public CompletableFuture<ResponseEntity<PermissionDTO>> getPermissionById(
      @PathVariable("id") String id) {
    return permissionService
        .getById(id)
        .thenApply(ResponseEntity::ok)
        .exceptionally(t -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
  }

  @PostMapping(path = {"", "/"})
  @Async(AsyncConfig.TASK_EXECUTOR_CONTROLLER)
  public CompletableFuture<ResponseEntity<PermissionDTO>> createPermission(
      @RequestBody @Valid PermissionDTO data) {
    return permissionService
        .create(data)
        .thenApply(res -> new ResponseEntity<PermissionDTO>(res, HttpStatus.CREATED))
        .exceptionally(t -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
  }

  @PutMapping("/{id}")
  @Async(AsyncConfig.TASK_EXECUTOR_CONTROLLER)
  public CompletableFuture<ResponseEntity<PermissionDTO>> updatePermission(
      @PathVariable("id") String id,
      @RequestBody @Valid PermissionDTO data) throws URISyntaxException {
    return permissionService
        .update(id, data)
        .thenApply(res -> new ResponseEntity<PermissionDTO>(res, HttpStatus.OK))
        .exceptionally(t -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
  }

  @DeleteMapping("/{id}")
  @Async(AsyncConfig.TASK_EXECUTOR_CONTROLLER)
  public CompletableFuture<ResponseEntity<HttpStatus>> deletePermission(
      @PathVariable("id") String id) {
    return permissionService
        .delete(id)
        .thenApply((res) -> new ResponseEntity<HttpStatus>(HttpStatus.OK))
        .exceptionally(t -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
  }
}
