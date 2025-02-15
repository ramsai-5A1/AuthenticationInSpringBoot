package com.ecommerce2.service;

import com.ecommerce2.dto.OrderResponse;

public interface OrderService {
    public OrderResponse placeOrder(String token, String userName);
}
