package com.ecommerce2.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidateTokenResponse {
    private String userName;
    private boolean isValid;
}
