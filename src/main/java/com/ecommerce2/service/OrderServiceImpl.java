package com.ecommerce2.service;

import org.springframework.stereotype.Service;

import com.ecommerce2.dto.OrderResponse;
import com.ecommerce2.dto.ValidateTokenResponse;
import com.ecommerce2.exception.ValidAuthTokenNotPresentException;
import com.ecommerce2.utils.JwtUtil;

@Service
public class OrderServiceImpl implements OrderService {

    JwtUtil jwtUtil;

    public OrderServiceImpl(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @Override
    public OrderResponse placeOrder(String token) {
        ValidateTokenResponse response = jwtUtil.validateToken(token);

        if (!response.isValid()) {
            throw new ValidAuthTokenNotPresentException("Token is incorrect or expired");
        }
        String userName = response.getUserName();
        return OrderResponse.builder()
            .message("Hey " + userName + " order placed successfully")
            .build();
    }

}
