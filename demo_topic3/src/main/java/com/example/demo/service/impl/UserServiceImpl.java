package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import com.example.demo.request.UserCreateReq;
import com.example.demo.response.UserResponse;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepo repo;

  @Autowired
  private ModelMapper mapper;

  @Autowired
  private PasswordEncoder endcode;

  @Override
  public List<UserResponse> getAll() {
    List<User> usersList = this.repo.findAll();

    return usersList.stream()
        .map(user -> mapper.map(user, UserResponse.class))
        .collect(Collectors.toList());
  }

  @Override
  public UserResponse getById(UUID id) {
    Optional<User> optional = this.repo.findById(id);
    try {
      User user = optional.orElseThrow(() -> new NoSuchElementException("User not found"));
      return mapper.map(user, UserResponse.class);
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public UserResponse create(UserCreateReq request) {
    User newUser = mapper.map(request, User.class);
    newUser.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
    newUser.setPassword(endcode.encode(request.getPassword()));
    User user = this.repo.save(mapper.map(request, User.class));
    return mapper.map(user, UserResponse.class);
  }

  @Override
  public void toggleStt(UUID id) {
    //...
  }

  @Override
  public void delete(UUID id) {
    this.repo.deleteById(id);
  }
}
