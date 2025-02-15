package com.ecommerce2.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderRequest {

    @NotBlank(message = "Username can't be left blank")
    private String userName;
}
