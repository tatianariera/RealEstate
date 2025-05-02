package com.tfg.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfg.backend.entity.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    
}
