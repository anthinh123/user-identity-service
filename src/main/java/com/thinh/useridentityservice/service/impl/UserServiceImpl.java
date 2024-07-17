package com.thinh.useridentityservice.service.impl;

import com.thinh.useridentityservice.dto.UserDto;
import com.thinh.useridentityservice.entity.UserEntity;
import com.thinh.useridentityservice.exception.ResourceNotFoundException;
import com.thinh.useridentityservice.exception.UserNameExistException;
import com.thinh.useridentityservice.mapper.UserMapper;
import com.thinh.useridentityservice.repository.UserRepository;
import com.thinh.useridentityservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserMapper userMapper;

    private UserRepository userRepository;

    @Override
    public UserDto signUp(UserDto userDto) throws UserNameExistException {
        if (userRepository.findByUserName(userDto.getUserName()).isPresent()) {
            throw new UserNameExistException("UserName already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(userDto.getPassword());
        UserEntity userEntity = userMapper.toEntityWithNewPassword(userDto, encryptedPassword);
        UserEntity savedUSer = userRepository.save(userEntity);
        return userMapper.toDto(savedUSer);
    }

//    @Override
//    public UserDto updateUserInfo(UserDto userDto) {
//        UserEntity existingUserEntity = userRepository.findByUserName(userDto.getUserName())
//                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userDto.getUserName()));
//        existingUserEntity.setUserName(userDto.getUserName());
//        existingUserEntity.setEmail(userDto.getEmail());
//        return userMapper.toDto(userRepository.save(existingUserEntity));
//    }

    @Override
    public void deleteUserInfo(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByUserName(username).orElseThrow(
                () -> new ResourceNotFoundException("User", "username", username)
        );
        return user;
    }
}
