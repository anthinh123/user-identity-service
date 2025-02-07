package com.thinh.useridentityservice.controller;

import com.thinh.useridentityservice.auth.TokenService;
import com.thinh.useridentityservice.dto.JwtDto;
import com.thinh.useridentityservice.dto.SignInDto;
import com.thinh.useridentityservice.dto.SignUpResponse;
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

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody @Valid UserDto userDto) {
        System.out.println("signUp triggered ");
        userService.signUp(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SignUpResponse("Created"));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtDto> signIn(@RequestBody @Valid SignInDto data) {
        System.out.println("signIn triggered");
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.userName(), data.password());
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
