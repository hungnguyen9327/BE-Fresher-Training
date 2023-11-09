package com.example.security_topic5.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService<T> {

  CompletableFuture<T> getById(String id);

  CompletableFuture<List<T>> getAll();

  CompletableFuture<Page<T>> findAllByPage(Pageable pageable);

  CompletableFuture<T> create(T data);

  CompletableFuture<T> update(String id, T data);

  CompletableFuture<Void> remove(String id);

  CompletableFuture<Void> delete(String id);

  CompletableFuture<Boolean> checkExistedUsername(String username);

  CompletableFuture<Boolean> checkExistedEmail(String email);
}
