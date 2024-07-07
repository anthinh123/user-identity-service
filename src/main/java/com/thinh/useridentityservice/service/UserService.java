package com.thinh.useridentityservice.service;

import com.thinh.useridentityservice.dto.UserDto;

public interface UserService {
    UserDto addUser(UserDto userDto);

    UserDto getUserById(long id);

    UserDto getUserByEmail(String email);

    UserDto updateUserInfo(UserDto userDto);

    void deleteUserInfo(long id);
}
