package com.ecommerce2.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce2.dto.OrderRequest;
import com.ecommerce2.dto.OrderResponse;
import com.ecommerce2.exception.ValidAuthTokenNotPresentException;
import com.ecommerce2.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder(@RequestHeader("Authorization") String authorizationHeader, @Valid @RequestBody OrderRequest request) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer")) {
            throw new ValidAuthTokenNotPresentException("Bearer token is not present");
        } 

        String token = authorizationHeader.substring(7);
        OrderResponse response = orderService.placeOrder(token, request.getUserName());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
