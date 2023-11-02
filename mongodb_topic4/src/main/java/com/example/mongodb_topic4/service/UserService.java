package com.example.mongodb_topic4.service;

import com.example.mongodb_topic4.request.SignInReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService<T> {

  T getById(String id);

  List<T> getAll();

  Page<T> getAllByPage(Pageable pageable);

  T create(SignInReq data);

  T update(String id, T data);

  void remove(String id);

  void delete(String id);
}
