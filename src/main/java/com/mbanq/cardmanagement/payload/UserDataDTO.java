package com.mbanq.cardmanagement.payload;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class UserDataDTO {

  @NotNull(message = "Name Not Null")
  private String name;
  @NotNull(message = "username Not Null")
  private String username;
  private String password;
  private String email;
  private Set<String> role;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Set<String> getRole() {
    return role;
  }

  public void setRole(Set<String> role) {
    this.role = role;
  }
}
