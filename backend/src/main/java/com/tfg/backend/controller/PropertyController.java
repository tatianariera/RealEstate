package com.tfg.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.backend.repository.PropertyRepository;
import com.tfg.backend.entity.Property;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {
    private final PropertyRepository propertyRepository;

    public PropertyController(PropertyRepository propertyRepository){
        this.propertyRepository = propertyRepository;
    }

    @GetMapping
    public List<Property> getAllProperties(){
        return propertyRepository.findAll();
    }

    @PostMapping
    public Property createProperty(@RequestBody Property property){
        return propertyRepository.save(property);
    }

}
