package com.mbanq.cardmanagement.security.service;

import com.mbanq.cardmanagement.model.User;
import com.mbanq.cardmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
   // final User user = userRepository.findByUsername(username);


    User user = userRepository.findByUsername(username)

            .orElseThrow(() ->
                    new UsernameNotFoundException("User Not Found with -> username or email : " + username)
     );



    return UserPrinciple.build(user);

    /*
    return org.springframework.security.core.userdetails.User//
        .withUsername(username)//
        .password(user.getPassword())//
        .authorities(user.getRoles())//
        .accountExpired(false)//
        .accountLocked(false)//
        .credentialsExpired(false)//
        .disabled(false)//
        .build();

        */
  }

}
