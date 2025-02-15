package com.ecommerce2.service;

import com.ecommerce2.dto.LoginResponse;
import com.ecommerce2.dto.UserRequest;
import com.ecommerce2.dto.UserResponse;

public interface AuthenticationService {

    public UserResponse handleSignup(UserRequest request);
    public LoginResponse handleLogin(UserRequest request);

}
