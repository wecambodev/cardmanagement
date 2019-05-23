package com.mbanq.cardmanagement.service;

import com.mbanq.cardmanagement.exception.CustomException;
import com.mbanq.cardmanagement.model.Role;
import com.mbanq.cardmanagement.model.RoleName;
import com.mbanq.cardmanagement.model.User;
import com.mbanq.cardmanagement.repository.RoleRepository;
import com.mbanq.cardmanagement.repository.UserRepository;
import com.mbanq.cardmanagement.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;


@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;


  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private AuthenticationManager authenticationManager;

  public String signin(String username, String password) {
    try {

      Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      username,
                      password
              )
      );

      SecurityContextHolder.getContext().setAuthentication(authentication);

      String token = jwtTokenProvider.generateJwtToken(authentication);
      return token;

    } catch (AuthenticationException e) {
      throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }


  public String signup(User user) {
    for(Role role : user.getRoles()){

    }
    if (!userRepository.existsByUsername(user.getUsername())) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      userRepository.save(user);

      return "Create new user success.";


    } else {
      throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public void delete(String username) {
    userRepository.deleteByUsername(username);
  }

  public User search(String username) {


    User user = userRepository.findByUsername(username)

            .orElseThrow(() ->
                    new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND)
            );

    return user;



/*    User user = userRepository.findByUsername(username).o
    if (user == null) {
      throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
    }
    return user;*/
  }

  public User whoami(HttpServletRequest req) {

    User user = userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)))

            .orElseThrow(() ->
                    new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND)
            );

    return user;
  }

  public String refresh(String username) {
    return "";
    //return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
  }



}
