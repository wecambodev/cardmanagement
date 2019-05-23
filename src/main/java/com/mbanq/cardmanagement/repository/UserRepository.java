package com.mbanq.cardmanagement.repository;

import com.mbanq.cardmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Integer> {

  boolean existsByUsername(String username);

  Optional<User> findByUsername(String username);

  @Transactional
  void deleteByUsername(String username);

}
