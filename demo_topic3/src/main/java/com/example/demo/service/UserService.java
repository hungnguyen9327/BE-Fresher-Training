package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.request.UserCreateReq;
import com.example.demo.response.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {

  List<UserResponse> getAll();

  UserResponse getById(UUID id);

  UserResponse create(UserCreateReq request);

  void toggleStt(UUID id);

  void delete(UUID id);

}
