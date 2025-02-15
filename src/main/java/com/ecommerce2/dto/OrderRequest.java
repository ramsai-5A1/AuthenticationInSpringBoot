package com.ecommerce2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OrderRequest {

    @NotBlank(message = "Please enter valid item name")
    private String itemName;

    @Positive(message = "Quantity should be positive value")
    private Long quantity;

    @NotBlank(message = "Please enter valid shipping address")
    private String shippingAddress;
}
