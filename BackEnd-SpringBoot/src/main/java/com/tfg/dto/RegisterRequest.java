package com.tfg.dto;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Data
public class RegisterRequest {
    
    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String password;
}
