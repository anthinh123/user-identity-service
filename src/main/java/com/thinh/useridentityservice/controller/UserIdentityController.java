package com.thinh.useridentityservice.controller;

import com.thinh.useridentityservice.auth.TokenService;
import com.thinh.useridentityservice.dto.JwtDto;
import com.thinh.useridentityservice.dto.SignInDto;
import com.thinh.useridentityservice.dto.UserDto;
import com.thinh.useridentityservice.entity.UserEntity;
import com.thinh.useridentityservice.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/auth")
public class UserIdentityController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/test")
    public ResponseEntity<String> getAllFlashCard() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid UserDto userDto) {
        UserDto savedUser = userService.signUp(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtDto> signIn(@RequestBody @Valid SignInDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.userName(), data.password());
        System.out.println("usernamePassword = " + usernamePassword);
        try {
            var authUser = authenticationManager.authenticate(usernamePassword);
            var accessToken = tokenService.generateAccessToken((UserEntity) authUser.getPrincipal());
            return ResponseEntity.ok(new JwtDto(accessToken));
        } catch (Exception e) {
            System.out.println("ex = " + e);
        }
        return ResponseEntity.ok(new JwtDto("error"));
    }

}
