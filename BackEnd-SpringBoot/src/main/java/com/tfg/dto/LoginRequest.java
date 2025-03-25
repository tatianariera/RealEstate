package com.tfg.dto;

import lombok.Data;
import jakarta.validation.constraints.NotEmpty;

@Data
public class LoginRequest {
    
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
