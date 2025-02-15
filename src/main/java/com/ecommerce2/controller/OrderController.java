package com.ecommerce2.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @PostMapping
    public ResponseEntity<Map<String, String>> placeOrder() {
        System.out.println("Placeorder api hit successfully");
        
        Map<String, String> response = new HashMap<>();
        response.put("order-status", "placed successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
