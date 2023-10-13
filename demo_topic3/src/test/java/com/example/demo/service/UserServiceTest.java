package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.model.User;
import com.example.demo.repo.RoleRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.response.UserResponse;
import com.example.demo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @Mock
  UserRepo userRepo;
  @Mock
  RoleRepo roleRepo;
  UserServiceImpl userService;

  @BeforeEach
  void setUp() {
    this.userService = new UserServiceImpl(this.userRepo);
  }

  @Test
  void getAllPerson() {
    userService.getAll();
    verify(userRepo).findAll();

  }

}
