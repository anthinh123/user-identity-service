package com.thinh.useridentityservice.repository;

import com.thinh.useridentityservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);

    Optional<UserDetails> findByUserName(String userName);
}
