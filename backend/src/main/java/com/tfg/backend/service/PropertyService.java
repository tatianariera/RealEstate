package com.tfg.backend.service;

import com.tfg.backend.entity.Property;
import com.tfg.backend.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public List<Property> getPropertiesForSale() {
        return propertyRepository.findByPublishDateBeforeAndTypeOrderByPublishDateDesc(LocalDate.now(),
                Property.PropertyType.SALE);
    }

    public List<Property> getPropertiesForRent() {
        return propertyRepository.findByPublishDateBeforeAndTypeOrderByPublishDateDesc(LocalDate.now(),
                Property.PropertyType.RENT);
    }

    public Optional<Property> getPropertyById(Long id) {
        return propertyRepository.findById(id);
    }

    public Property save(Property property) {
        return propertyRepository.save(property);
    }

}
