package com.tfg.backend.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
}
