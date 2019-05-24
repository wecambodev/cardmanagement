package com.mbanq.cardmanagement.security.repository;


import com.mbanq.cardmanagement.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
