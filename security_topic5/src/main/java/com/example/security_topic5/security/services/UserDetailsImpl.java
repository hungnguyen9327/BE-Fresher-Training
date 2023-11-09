package com.example.security_topic5.security.services;

import com.example.security_topic5.model.Role;
import com.example.security_topic5.model.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {

  @Getter
  String _id;

  String username;

  @Getter
  String email;

  @JsonIgnore
  String password;

  Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(
      String _id, String username, String email, String password,
      Collection<? extends GrantedAuthority> authorities) {
    this._id = _id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
  }

  public static UserDetailsImpl build(User user) {
//    List<GrantedAuthority> authorities = user.getRoles().stream()
//        .map(role -> new SimpleGrantedAuthority(role.getName().toString()))
//        .collect(Collectors.toList());

    return new UserDetailsImpl(
        user.get_id(),
        user.getUsername(),
        user.getEmail(),
        user.getPassword(),
        getGrantedAuthorities(new ArrayList<>(user.getRoles()))
    );
  }

  private static List<GrantedAuthority> getGrantedAuthorities(List<Role> roles) {
    return roles.stream()
        .flatMap(role -> role.getPermissions().stream())
        .map(permission -> new SimpleGrantedAuthority(permission.getName()))
        .collect(Collectors.toList());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserDetailsImpl user = (UserDetailsImpl) o;
    return Objects.equals(_id, user._id);
  }
}
