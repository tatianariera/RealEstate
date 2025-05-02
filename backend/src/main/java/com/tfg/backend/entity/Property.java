package com.tfg.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String location;
    private Double price;
    private String type;
    private Integer bedrooms;
    private Integer bathrooms;
    private Double area;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public Property(){}
}
