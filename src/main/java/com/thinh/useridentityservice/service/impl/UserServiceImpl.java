package com.thinh.useridentityservice.service.impl;

import com.thinh.useridentityservice.dto.UserDto;
import com.thinh.useridentityservice.entity.UserEntity;
import com.thinh.useridentityservice.exception.ResourceNotFoundException;
import com.thinh.useridentityservice.mapper.UserMapper;
import com.thinh.useridentityservice.repository.UserRepository;
import com.thinh.useridentityservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    private UserRepository userRepository;

    @Override
    public UserDto addUser(UserDto userDto) {
        UserEntity userEntity = userMapper.toEntity(userDto);
        UserEntity savedUSer = userRepository.save(userEntity);
        return userMapper.toDto(savedUSer);
    }

    @Override
    public UserDto getUserById(long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return userMapper.toDto(userEntity);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return null;
    }

    @Override
    public UserDto updateUserInfo(UserDto userDto) {
        UserEntity existingUserEntity = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userDto.getId()));
        existingUserEntity.setFirstName(userDto.getFirstName());
        existingUserEntity.setLastName(userDto.getLastName());
        existingUserEntity.setEmail(userDto.getEmail());
        return userMapper.toDto(userRepository.save(existingUserEntity));
    }

    @Override
    public void deleteUserInfo(long id) {
        userRepository.deleteById(id);
    }

}
