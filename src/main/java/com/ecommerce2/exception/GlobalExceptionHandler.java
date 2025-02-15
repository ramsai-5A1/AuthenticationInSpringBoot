package com.ecommerce2.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ecommerce2.dto.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorDetails> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        System.out.println("[GlobalExceptionHandler] Handling via InvalidCredentialsException Method");
        ErrorDetails response = ErrorDetails.builder()
                            .statusCode(HttpStatus.UNAUTHORIZED.value())
                            .message(ex.getMessage())
                            .reason("Invalid details")
                            .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleUserNotFoundException(UserNotFoundException ex) {
        System.out.println("[GlobalExceptionHandler] Handling via UserNotFoundException Method");
        ErrorDetails response = ErrorDetails.builder()
                            .statusCode(HttpStatus.NOT_FOUND.value())
                            .message(ex.getMessage())
                            .reason("User not found in database")
                            .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        System.out.println("[GlobalExceptionHandler] Handling via MethodArgumentNotValidException Method");
        ErrorDetails response = ErrorDetails.builder()
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .message(ex.getMessage())
                            .reason("Something went wrong once check the request body for validations")
                            .build();

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        response.setErrors(errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGeneralException(Exception ex) {
        System.out.println("[GlobalExceptionHandler] Handling via General Exception Method");
        ErrorDetails response = ErrorDetails.builder()
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .message(ex.getMessage())
                            .reason("Something went wrong")
                            .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
