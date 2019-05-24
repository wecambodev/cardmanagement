package com.mbanq.cardmanagement.payload.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserSignUpRequest {

  @NotNull(message = "Name Not Null")
  private String name;
  @NotNull(message = "username Not Null")
  private String username;
  @NotNull(message = "password Not Null")
  private String password;
  @NotNull(message = "email Not Null")
  private String email;
  @NotNull(message = "role Not Null")
  private String role;
}
