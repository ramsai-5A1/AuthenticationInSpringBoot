package com.ecommerce2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce2.dto.LoginResponse;
import com.ecommerce2.dto.UserRequest;
import com.ecommerce2.dto.UserResponse;
import com.ecommerce2.service.AuthenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth/user")
@Tag(name = "Authentication Controller", description = "This Controller contains APIs for Login and Signup of user")
public class AuthenticationController {

    AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    @Operation(summary = "Handle Signup", description = "This API is responsible for collecting details of user and creates new record in database")
    public ResponseEntity<UserResponse> handleSignup(@Valid @RequestBody UserRequest request) {
        UserResponse response = authenticationService.handleSignup(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    @Operation(summary = "Handle Login", description = "This API is responsibile for validating the password, if succeeds then returns back a JWT token with which we can make subsequent protected routes")
    public ResponseEntity<LoginResponse> handleLogin(@Valid @RequestBody UserRequest request) {
        LoginResponse response = authenticationService.handleLogin(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
