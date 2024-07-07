package com.thinh.useridentityservice.repository;

import com.thinh.useridentityservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
