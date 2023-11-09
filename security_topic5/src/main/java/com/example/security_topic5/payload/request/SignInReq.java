package com.example.security_topic5.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInReq {

  //  @NotBlank(message = "Invalid Username: Empty username")
//  @NotNull(message = "Invalid Username: Username is NULL")
//  @Pattern(regexp = "^[a-z0-9_-]{3,15}$", message = "Invalid Username: Must be of 6 - 15 characters")
  private String username;

  //  @NotBlank(message = "Invalid Password: Empty password")
//  @NotNull(message = "Invalid Password: Password is NULL")
//  @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$",
//      message = "Invalid Password: At least 8 character, one lowercase, one uppercase and one special character.")
  private String password;
}
