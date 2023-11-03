package com.example.mongodb_topic4.controller;

import com.example.mongodb_topic4.dto.UserDTO;
import com.example.mongodb_topic4.model.SortField;
import com.example.mongodb_topic4.request.SignInReq;
import com.example.mongodb_topic4.service.UserService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

  private final UserService<UserDTO> userService;

  @GetMapping("/")
  public ResponseEntity<List<UserDTO>> getAllUsers() {
    return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
  }

  @GetMapping("/paging")
  public Page<UserDTO> getAllByPage(
      @RequestParam(required = false/*, defaultValue="0"*/) @Min(0) @Max(25) Integer page,
      @RequestParam(required = false/*, defaultValue="2"*/) Integer sizePerPage,
      @RequestParam(required = false/*, defaultValue="ID"*/) SortField sortField,
      @RequestParam(required = false/*, defaultValue="DESC"*/) Sort.Direction sortDirection) {
    Pageable pageable = PageRequest.of(
        page == null ? 0 : (int) page,
        sizePerPage == null ? 2 : (int) sizePerPage,
        sortDirection == null ? Direction.ASC : sortDirection,
        sortField == null ? SortField.ID.getDatabaseFieldName() : sortField.getDatabaseFieldName()
    );
    return userService.getAllByPage(pageable);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getUserById(@PathVariable("id") String id) {
    return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
  }

  @PostMapping("/")
  public ResponseEntity<UserDTO> createUser(@RequestBody SignInReq data) throws URISyntaxException {
    return new ResponseEntity<>(userService.create(data), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserDTO> updateUser(
      @PathVariable("id") String id,
      @RequestBody UserDTO data) throws URISyntaxException {
    return new ResponseEntity<>(userService.update(id, data), HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") String id) {
    userService.delete(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
