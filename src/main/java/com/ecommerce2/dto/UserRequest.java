package com.ecommerce2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRequest {

    @NotBlank(message = "Please enter valid user-name")
    private String userName;

    @Pattern(regexp = "^[a-zA-Z0-9@!#$]{6,20}$", message = "Please use alphabets, digits, allowed special characters (!, @, #, $)")
    @NotBlank(message = "Please enter valid password")
    private String password;
}
