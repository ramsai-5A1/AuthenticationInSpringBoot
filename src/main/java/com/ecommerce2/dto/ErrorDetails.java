package com.ecommerce2.dto;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDetails {

    private int statusCode;
    private String message;
    private String reason;
    Map<String, String> errors;

}
