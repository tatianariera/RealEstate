package com.tfg.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private int bedrooms;
    private int bathrooms;
    private boolean parking;
    private boolean pool;
    private double sqmt;
    private String floor;
    private boolean elevator;
    private double price;
    private String location;
    private String imageUrl;
    private LocalDate publishDate;

    @Enumerated(EnumType.STRING)
    private PropertyType type; 

    public enum PropertyType {
        SALE, RENT
    }
}
