package com.ecommerce2.dto;

import lombok.Builder;

@Builder
public class LoginResponse {
    private Long userId;
    private String userName;
    private String token;
    private String message;
}
