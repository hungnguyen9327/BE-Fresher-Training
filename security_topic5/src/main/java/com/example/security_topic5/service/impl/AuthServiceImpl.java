package com.example.security_topic5.service.impl;

import com.example.security_topic5.config.AsyncConfig;
import com.example.security_topic5.dto.RoleDTO;
import com.example.security_topic5.dto.UserDTO;
import com.example.security_topic5.exception.ExistedException;
import com.example.security_topic5.model.RefreshToken;
import com.example.security_topic5.payload.request.SignInReq;
import com.example.security_topic5.payload.request.SignUpReq;
import com.example.security_topic5.payload.response.InfoResponse;
import com.example.security_topic5.payload.response.JwtResponse;
import com.example.security_topic5.payload.response.RefTokenResponse;
import com.example.security_topic5.security.jwt.JwtUtils;
import com.example.security_topic5.security.services.UserDetailsImpl;
import com.example.security_topic5.service.AuthService;
import com.example.security_topic5.service.AuthorizationService;
import com.example.security_topic5.service.RefTokenService;
import com.example.security_topic5.service.UserService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

  AuthenticationManager authenticationManager;
  JwtUtils jwtUtils;
  UserService<UserDTO> userService;

  @Qualifier("roleService")
  AuthorizationService<RoleDTO> roleService;

  RefTokenService<RefreshToken> refTokenService;

  ModelMapper modelMapper;

  @Override
  @Async(AsyncConfig.TASK_EXECUTOR_SERVICE)
  public CompletableFuture<JwtResponse> signInUser(SignInReq signInReq)
      throws ExecutionException, InterruptedException {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(signInReq.getUsername(),
            signInReq.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> authorities = userDetails.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.toList());

    UserDTO user = userService
        .getById(userDetails.get_id())
        .thenApply(res -> res)
        .get();
    RefreshToken existingRefToken = refTokenService.getByUser(user);
    if (existingRefToken != null) {
      refTokenService.deleteByUserId(userDetails.get_id());
    }
    String refreshToken = refTokenService.createRefreshToken(
        modelMapper.map(userDetails.get_id(), UserDTO.class)).getToken();

    return CompletableFuture.completedFuture(new JwtResponse(
        jwt, refreshToken,
        userDetails.get_id(),
        userDetails.getUsername(),
        userDetails.getEmail(),
        authorities
    ));
  }

  @Override
  @Async(AsyncConfig.TASK_EXECUTOR_SERVICE)
  public CompletableFuture<InfoResponse> signUpUser(SignUpReq signUpReq)
      throws ExecutionException, InterruptedException {
    Boolean isExistedUsername = userService.checkExistedUsername(signUpReq.getUsername())
        .thenApply(res -> res).get();
    Boolean isExistedEmail = userService.checkExistedEmail(signUpReq.getEmail())
        .thenApply(res -> res).get();
    if (isExistedUsername) {
      throw new ExistedException("Username is already taken!");
    }

    if (isExistedEmail) {
      throw new ExistedException("Email is already in use!");
    }

    Set<RoleDTO> roles = new HashSet<>();
    roles.add(
        roleService.getByName("ROLE_USER")
            .thenApply(res -> res)
            .get()
    );

    UserDTO newUser = new UserDTO();
    newUser.setEmail(signUpReq.getEmail());
    newUser.setUsername(signUpReq.getUsername());
    newUser.setPassword(signUpReq.getPassword());
    newUser.setRoles(roles);

    userService.create(newUser);

    return CompletableFuture.completedFuture(new InfoResponse(
        "User registered successfully!",
        "success"
    ));
  }
}
