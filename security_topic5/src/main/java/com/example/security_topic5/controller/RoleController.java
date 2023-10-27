package com.example.security_topic5.controller;

import com.example.security_topic5.config.AsyncConfig;
import com.example.security_topic5.dto.RoleDTO;
import com.example.security_topic5.dto.UserDTO;
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
@RequestMapping("/api/roles")
public class RoleController {

  @Qualifier("roleService")
  AuthorizationService<RoleDTO> roleService;

  @GetMapping(path = {"", "/"})
  @Async(AsyncConfig.TASK_EXECUTOR_CONTROLLER)
  public CompletableFuture<ResponseEntity<List<RoleDTO>>> getAllRoles() {
    return roleService
        .getAll()
        .thenApply(ResponseEntity::ok)
        .exceptionally(t -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
  }

  @GetMapping("/{id}")
  @Async(AsyncConfig.TASK_EXECUTOR_CONTROLLER)
  public CompletableFuture<ResponseEntity<RoleDTO>> getRoleById(@PathVariable("id") String id) {
    return roleService
        .getById(id)
        .<ResponseEntity<RoleDTO>>thenApply(ResponseEntity::ok)
        .exceptionally(t -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
  }

  @PostMapping(path = {"", "/"})
  @Async(AsyncConfig.TASK_EXECUTOR_CONTROLLER)
  public CompletableFuture<ResponseEntity<RoleDTO>> createRole(@RequestBody @Valid RoleDTO data) {
    return roleService
        .create(data)
        .thenApply(res -> new ResponseEntity<RoleDTO>(res, HttpStatus.CREATED))
        .exceptionally(t -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
  }

  @PutMapping("/{id}")
  @Async(AsyncConfig.TASK_EXECUTOR_CONTROLLER)
  public CompletableFuture<ResponseEntity<RoleDTO>> updateRole(
      @PathVariable("id") String id,
      @RequestBody @Valid RoleDTO data) throws URISyntaxException {
    return roleService
        .update(id, data)
        .thenApply(res -> new ResponseEntity<RoleDTO>(res, HttpStatus.OK))
        .exceptionally(t -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
  }

  @DeleteMapping("/{id}")
  @Async(AsyncConfig.TASK_EXECUTOR_CONTROLLER)
  public CompletableFuture<ResponseEntity<HttpStatus>> deleteRole(@PathVariable("id") String id) {
    return roleService
        .delete(id)
        .thenApply((res) -> new ResponseEntity<HttpStatus>(HttpStatus.OK))
        .exceptionally(t -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
  }
}
