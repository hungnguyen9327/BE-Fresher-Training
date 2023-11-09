package com.example.security_topic5.controller;

import com.example.security_topic5.config.AsyncConfig;
import com.example.security_topic5.exception.NotFoundException;
import com.example.security_topic5.model.RefreshToken;
import com.example.security_topic5.payload.request.RefTokenReq;
import com.example.security_topic5.payload.request.SignInReq;
import com.example.security_topic5.payload.request.SignUpReq;
import com.example.security_topic5.payload.response.InfoResponse;
import com.example.security_topic5.payload.response.JwtResponse;
import com.example.security_topic5.payload.response.RefTokenResponse;
import com.example.security_topic5.security.jwt.JwtUtils;
import com.example.security_topic5.service.AuthService;
import com.example.security_topic5.service.RefTokenService;
import jakarta.validation.Valid;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

  AuthService authService;
  RefTokenService<RefreshToken> refService;
  JwtUtils jwtUtils;

  @PostMapping("/sign-in")
  @Async(AsyncConfig.TASK_EXECUTOR_CONTROLLER)
  public CompletableFuture<ResponseEntity<JwtResponse>> signInUser(
      @Valid @RequestBody SignInReq signInReq) throws ExecutionException, InterruptedException {
    return authService
        .signInUser(signInReq)
        .thenApply(ResponseEntity::ok)
        .exceptionally(t -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
  }

  @PostMapping("/sign-up")
  @Async(AsyncConfig.TASK_EXECUTOR_CONTROLLER)
  public CompletableFuture<ResponseEntity<InfoResponse>> registerUser(
      @Valid @RequestBody SignUpReq signUpReq)
      throws ExecutionException, InterruptedException {
    return authService
        .signUpUser(signUpReq)
        .thenApply(ResponseEntity::ok)
        .exceptionally(t -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
  }

  @PostMapping("/refresh-token")
  @Async(AsyncConfig.TASK_EXECUTOR_CONTROLLER)
  public CompletableFuture<ResponseEntity<?>> refreshToken(@Valid @RequestBody RefTokenReq token) {
    return CompletableFuture.completedFuture(
        refService.getByToken(token.getRefreshToken())
            .map(refService::verifyExpiration)
            .map(RefreshToken::getUser)
            .map(user -> {
              String newToken = jwtUtils.generateTokenFromUsername(user.getUsername());
              return ResponseEntity.ok(new RefTokenResponse(newToken, token.getRefreshToken()));
            })
            .orElseThrow(() -> new NotFoundException("Refresh token is not in db"))
    );
  }
}
