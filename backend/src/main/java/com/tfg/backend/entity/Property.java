package com.tfg.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private int bedrooms;
    private int bathrooms;
    private boolean parking;
    private boolean pool;
    private double sqmt;
    private String floor;
    private boolean elevator;
    private double price;
    private LocalDate publishDate;

    @Enumerated(EnumType.STRING)
    private PropertyType type;

    public enum PropertyType {
        SALE, RENT
    }

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Image> images;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonIgnoreProperties("properties") 
    private Employee employee;

}
