package com.example.pubsubredis.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("user")
public class User implements Serializable {

  public User() {
  }

  public User(String id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }

  private String id;
  private String name;
  private String email;

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }
}
