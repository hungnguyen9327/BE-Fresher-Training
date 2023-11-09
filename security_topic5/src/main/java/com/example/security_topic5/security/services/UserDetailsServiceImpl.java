package com.example.security_topic5.security.services;

import com.example.security_topic5.exception.NotFoundException;
import com.example.security_topic5.model.User;
import com.example.security_topic5.repo.UserRepo;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  UserRepo userRepo;

  @Override
  public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
    Optional<User> optional;
    try {
      optional = userRepo.findByUsernameOrEmail(usernameOrEmail)
          .thenApply(u -> u).get();
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }
    User user = optional.orElseThrow(
        () -> new NotFoundException("User not found with " + usernameOrEmail + " login name. "));

    return UserDetailsImpl.build(user);
  }
}
