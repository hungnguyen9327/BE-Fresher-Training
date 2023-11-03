package com.example.mongodb_topic4.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInReq {

  private String username;
  private String email;
  private String password;
}