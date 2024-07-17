package com.thinh.useridentityservice.mapper;

import com.thinh.useridentityservice.dto.UserDto;
import com.thinh.useridentityservice.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(UserEntity userEntity) {
        return UserDto.builder()
                .userName(userEntity.getUsername())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .build();
    }

    public UserEntity toEntityWithNewPassword(UserDto userDto, String encryptedPwd) {
        return UserEntity.builder()
                .userName(userDto.getUserName())
                .email(userDto.getEmail())
                .password(encryptedPwd)
                .build();
    }
}
