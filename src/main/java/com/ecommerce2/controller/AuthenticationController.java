package com.ecommerce2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce2.dto.UserRequest;
import com.ecommerce2.dto.UserResponse;
import com.ecommerce2.service.AuthenticationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth/user")
public class AuthenticationController {

    AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> handleSignup(@Valid @RequestBody UserRequest request) {
        UserResponse response = authenticationService.handleSignup(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> handleLogin(@Valid @RequestBody UserRequest request) {
        UserResponse response = authenticationService.handleLogin(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
