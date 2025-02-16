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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/order")
@Tag(name = "Order Controller", description = "This Controller contains all the protected API's which can be hit only with valid JWT Token, once token is valid, then we can placeOrder and hit other apis")
public class OrderController {

    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @Operation(summary = "Place Order API", description = "This is a protected API, which succeeds only if proper valid token is provided in Authorization section in postman. Once succeeds, then places the order")
    public ResponseEntity<OrderResponse> placeOrder(@RequestHeader("Authorization") String authorizationHeader, @Valid @RequestBody OrderRequest request) {
        System.out.println("Reaching /placeOrder api controller");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer")) {
            throw new ValidAuthTokenNotPresentException("Bearer token is not present");
        } 

        String token = authorizationHeader.substring(7);
        OrderResponse response = orderService.placeOrder(token);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
