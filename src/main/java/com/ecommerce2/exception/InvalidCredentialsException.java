package com.ecommerce2.exception;

public class InvalidCredentialsException extends RuntimeException  {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
