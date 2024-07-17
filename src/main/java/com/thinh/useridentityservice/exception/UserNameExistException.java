package com.thinh.useridentityservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class UserNameExistException extends RuntimeException {

    public UserNameExistException(String message) {
        super(message);
    }
}
