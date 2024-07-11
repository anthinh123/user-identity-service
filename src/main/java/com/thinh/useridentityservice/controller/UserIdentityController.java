package com.thinh.useridentityservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/user")
public class UserIdentityController {

    @GetMapping()
    public ResponseEntity<String> getAllFlashCard() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
