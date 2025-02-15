package com.ecommerce2.exception;

public class ValidAuthTokenNotPresentException extends RuntimeException {
    public ValidAuthTokenNotPresentException(String message) {
        super(message);
    }

}
