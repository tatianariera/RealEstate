package com.tfg.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.backend.entity.Property;
import com.tfg.backend.repository.PropertyRepository;

@Service
public class PropertyService {
    
    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository){
        this.propertyRepository = propertyRepository;
    }

    public Property addProperty(Property property){
        return propertyRepository.save(property);
    }

    public List<Property> getAllProperties(){
        return propertyRepository.findAll();
    } 

    public Property getPropertyById(Long id){
        Optional<Property> property = propertyRepository.findById(id);
        return property.orElseThrow(() -> new RuntimeException("Property not found"));
    }

}
