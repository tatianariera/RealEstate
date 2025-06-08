package com.tfg.backend.service;

import com.tfg.backend.entity.Property;
import com.tfg.backend.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public List<Property> getPropertiesForSale() {
        return propertyRepository.findByTypeOrderByPublishDateDesc(Property.PropertyType.SALE);
    }

    public List<Property> getPropertiesForRent() {
        return propertyRepository.findByTypeOrderByPublishDateDesc(Property.PropertyType.RENT);
    }

    public Optional<Property> getPropertyById(Long id) {
        return propertyRepository.findById(id);
    }

    public Property saveProperty(Property property) {
        return propertyRepository.save(property);
    }
}
