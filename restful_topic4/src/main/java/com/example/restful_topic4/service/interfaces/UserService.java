package com.example.restful_topic4.service.interfaces;

import com.example.restful_topic4.enums.UserStatus;
import com.example.restful_topic4.request.SignUpReq;

import java.util.List;
import java.util.UUID;

public interface UserService<T> {

  List<T> getAll();

  T getById(UUID id);

  T signup(SignUpReq user);
  
  T addUser(T user);

  T replaceUser(UUID id, T user);

  T updateStatus(UUID id, UserStatus status);

  void delete(UUID id);
}


