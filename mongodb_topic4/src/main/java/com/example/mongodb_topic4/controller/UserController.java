package com.example.mongodb_topic4.controller;

import com.example.mongodb_topic4.dto.UserDTO;
import com.example.mongodb_topic4.model.SortField;
import com.example.mongodb_topic4.request.SignInReq;
import com.example.mongodb_topic4.service.UserService;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("/api/users")
public class UserController {

  @Value("${max-page}")
  private int maxPage;
  @Value("${min-page}")
  private int minPage;
  UserService<UserDTO> userService;


  public UserController(UserService<UserDTO> userService) {
    this.userService = userService;
  }

  @GetMapping("/")
  public ResponseEntity<List<UserDTO>> getAllUsers() {
    return new ResponseEntity<List<UserDTO>>(userService.getAll(), HttpStatus.OK);
  }

  @GetMapping("/paging")
  public Page<UserDTO> getAllByPage(
      @RequestParam(required = false/*, defaultValue="0"*/) Integer page,
      @RequestParam(required = false/*, defaultValue="2"*/) Integer sizePerPage,
      @RequestParam(required = false/*, defaultValue="ID"*/) SortField sortField,
      @RequestParam(required = false/*, defaultValue="DESC"*/) Sort.Direction sortDirection) {
    Pageable pageable = PageRequest.of(
        page == null ? 0 : (int) page,
        sizePerPage == null ? 2 : (int) sizePerPage,
        sortDirection == null ? Direction.ASC : sortDirection,
        sortField == null ? SortField.ID.getDatabaseFieldName() : sortField.getDatabaseFieldName()
    );
    return userService.findAllByPage(minPage, maxPage, pageable);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getUserById(@PathVariable("id") String id) {
    return new ResponseEntity<UserDTO>(userService.getById(id), HttpStatus.OK);
  }

  @PostMapping("/")
  public ResponseEntity<UserDTO> createUser(@RequestBody SignInReq data) throws URISyntaxException {
    return new ResponseEntity<UserDTO>(userService.create(data), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserDTO> updateUser(
      @PathVariable("id") String id,
      @RequestBody UserDTO data) throws URISyntaxException {
    return new ResponseEntity<UserDTO>(userService.update(id, data), HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") String id) {
    userService.delete(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
