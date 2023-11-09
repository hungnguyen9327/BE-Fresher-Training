package com.example.security_topic5.controller;

import com.example.security_topic5.config.AsyncConfig;
import com.example.security_topic5.dto.UserDTO;
import com.example.security_topic5.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

  UserService<UserDTO> userService;
  AuthenticationManager authenticationManager;

//  @GetMapping(path = {"", "/"})
//  public CompletableFuture<ResponseEntity<?>> test() {
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//    return CompletableFuture.completedFuture(new ResponseEntity<>(userDetails, HttpStatus.OK));
//  }
//
//  @GetMapping("/test2")
//  public ResponseEntity<?> test2() {
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//
//    return new ResponseEntity<>(userDetails.getAuthorities(), HttpStatus.OK);
//  }

  @GetMapping(path = {"", "/"})
  @Async(AsyncConfig.TASK_EXECUTOR_CONTROLLER)
  public CompletableFuture<ResponseEntity<List<UserDTO>>> getAllUsers() {
    return userService
        .getAll()
        .thenApply(ResponseEntity::ok)
        .exceptionally(t -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
  }

  @GetMapping("/paging")
  @Async(AsyncConfig.TASK_EXECUTOR_CONTROLLER)
  public CompletableFuture<ResponseEntity<Page<UserDTO>>> getAllByPage(
      @RequestParam(required = false/*, defaultValue="0"*/) @Max(50) @Min(0) Integer page,
      @RequestParam(required = false/*, defaultValue="2"*/) Integer sizePerPage,
      @RequestParam(required = false/*, defaultValue="_id"*/) String sortField,
      @RequestParam(required = false/*, defaultValue="DESC"*/) Sort.Direction sortDirection) {
    Pageable pageable = PageRequest.of(
        page == null ? 0 : (int) page,
        sizePerPage == null ? 2 : (int) sizePerPage,
        sortDirection == null ? Direction.ASC : sortDirection,
        sortField == null ? "ID" : sortField
    );
    return userService
        .findAllByPage(pageable)
        .thenApply(ResponseEntity::ok)
        .exceptionally(t -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
  }

  @GetMapping("/{id}")
  @Async(AsyncConfig.TASK_EXECUTOR_CONTROLLER)
  public CompletableFuture<ResponseEntity<UserDTO>> getUserById(@PathVariable("id") String id) {
    return userService
        .getById(id)
        .thenApply(ResponseEntity::ok)
        .exceptionally(t -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
  }

  @PostMapping(path = {"", "/"})
  @Async(AsyncConfig.TASK_EXECUTOR_CONTROLLER)
  public CompletableFuture<ResponseEntity<UserDTO>> createUser(@RequestBody @Valid UserDTO data) {
    return userService
        .create(data)
        .thenApply(res -> new ResponseEntity<UserDTO>(res, HttpStatus.CREATED))
        .exceptionally(t -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
  }

  @PutMapping("/{id}")
  @Async(AsyncConfig.TASK_EXECUTOR_CONTROLLER)
  public CompletableFuture<ResponseEntity<UserDTO>> updateUser(
      @PathVariable("id") String id,
      @RequestBody @Valid UserDTO data) throws URISyntaxException {
    return userService
        .update(id, data)
        .thenApply(res -> new ResponseEntity<UserDTO>(res, HttpStatus.OK))
        .exceptionally(t -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
  }

  @PatchMapping("/{id}")
  @Async(AsyncConfig.TASK_EXECUTOR_CONTROLLER)
  public CompletableFuture<ResponseEntity<HttpStatus>> removeUser(@PathVariable("id") String id) {
    return userService
        .remove(id)
        .thenApply((res) -> new ResponseEntity<HttpStatus>(HttpStatus.OK))
        .exceptionally(t -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
  }

  @DeleteMapping("/{id}")
  @Async(AsyncConfig.TASK_EXECUTOR_CONTROLLER)
  public CompletableFuture<ResponseEntity<HttpStatus>> deleteUser(@PathVariable("id") String id) {
    return userService
        .delete(id)
        .thenApply((res) -> new ResponseEntity<HttpStatus>(HttpStatus.OK))
        .exceptionally(t -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
  }
}

