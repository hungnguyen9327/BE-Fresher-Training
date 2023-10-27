package com.example.security_topic5.service;

import com.example.security_topic5.payload.request.SignInReq;
import com.example.security_topic5.payload.request.SignUpReq;
import com.example.security_topic5.payload.response.InfoResponse;
import com.example.security_topic5.payload.response.JwtResponse;
import com.example.security_topic5.payload.response.RefTokenResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface AuthService {

  CompletableFuture<JwtResponse> signInUser(SignInReq signInReq)
      throws ExecutionException, InterruptedException;


  CompletableFuture<InfoResponse> signUpUser(SignUpReq signUpReq)
      throws ExecutionException, InterruptedException;
}
