package com.mbanq.cardmanagement.controller;
import com.mbanq.cardmanagement.exception.InvalidRequestException;
import com.mbanq.cardmanagement.model.Role;
import com.mbanq.cardmanagement.model.RoleName;
import com.mbanq.cardmanagement.payload.UserDataDTO;
import com.mbanq.cardmanagement.payload.UserResponseDTO;
import com.mbanq.cardmanagement.model.User;
import com.mbanq.cardmanagement.service.RoleService;
import com.mbanq.cardmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private UserService userService;

  @Autowired
  private RoleService roleService;

  @Autowired
  private ModelMapper modelMapper;

  @PostMapping("/signin")

  public String login(@RequestParam String username, //
                      @RequestParam String password) {
    return userService.signin(username, password);
  }

  @PostMapping("/signup")
  public String signup(@Valid @RequestBody UserDataDTO user,  BindingResult result) {
      if(result.hasErrors()){
          throw new InvalidRequestException("Invalid Signup", result);
      }

      System.out.println("Hellos");

    User newUser =  modelMapper.map(user, User.class);

    Set<Role> roles = new HashSet<>();
    Set<String> inputRoles = user.getRole();



    inputRoles.forEach(role -> {
      switch(role) {
        case "admin":
          Role adminRole = roleService.findByName(RoleName.ROLE_ADMIN)
                  .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
          roles.add(adminRole);

          break;
        default:
          Role userRole = roleService.findByName(RoleName.ROLE_USER)
                  .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
          roles.add(userRole);
      }
    });

    newUser.setRoles(roles);


    return userService.signup(newUser);
  }




  @DeleteMapping(value = "/{username}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String delete(@PathVariable String username) {
    userService.delete(username);
    return username;
  }

  @GetMapping(value = "/{username}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")

  public UserResponseDTO search(@PathVariable String username) {
    return modelMapper.map(userService.search(username), UserResponseDTO.class);
  }

  @GetMapping(value = "/me")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")

  public UserResponseDTO whoami(HttpServletRequest req) {
    return modelMapper.map(userService.whoami(req), UserResponseDTO.class);
  }

  @GetMapping("/refresh")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
  public String refresh(HttpServletRequest req) {
    return userService.refresh(req.getRemoteUser());
  }

}
