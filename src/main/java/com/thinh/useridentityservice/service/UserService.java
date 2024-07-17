package com.thinh.useridentityservice.service;

import com.thinh.useridentityservice.dto.UserDto;

public interface UserService {
    UserDto signUp(UserDto userDto);

    void deleteUserInfo(long id);
}
