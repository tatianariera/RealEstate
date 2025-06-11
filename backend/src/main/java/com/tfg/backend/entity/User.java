package com.tfg.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class User {
    @Id 
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToOne
    private Employee employee;
}