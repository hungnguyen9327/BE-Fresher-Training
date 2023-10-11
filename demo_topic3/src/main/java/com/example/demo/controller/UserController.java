package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.request.UserCreateReq;
import com.example.demo.response.UserResponse;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  private UserServiceImpl userService;

  @GetMapping("/all")
  public ResponseEntity<List<UserResponse>> getAllUsers() {
    try {
      return new ResponseEntity<List<UserResponse>>(this.userService.getAll(), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponse> getUser(@PathVariable("id") UUID id) {
    try {
      return new ResponseEntity<UserResponse>(this.userService.getById(id), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/add")
  public ResponseEntity<?> saveUser(@RequestBody UserCreateReq request) throws URISyntaxException {
    try {
      UserResponse result = this.userService.create(request);
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
  }
}
