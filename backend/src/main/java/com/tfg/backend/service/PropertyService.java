package com.tfg.backend.service;

import com.tfg.backend.entity.Employee;
import com.tfg.backend.entity.Property;
import com.tfg.backend.entity.Location;
import com.tfg.backend.repository.EmployeeRepository;
import com.tfg.backend.repository.PropertyRepository;
import com.tfg.backend.repository.LocationRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PropertyService {

    private final PropertyRepository propertyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private LocationRepository locationRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public List<Property> getPropertiesForSale() {
        return propertyRepository.findByTypeOrderByPublishDateDesc(Property.PropertyType.SALE);
    }

    public List<Property> getPropertiesForRent() {
        return propertyRepository.findByTypeOrderByPublishDateDesc(Property.PropertyType.RENT);
    }

    public Optional<Property> getPropertyById(Integer id) {
        return propertyRepository.findById(id);
    }

    public Property saveProperty(Property property) {
        return propertyRepository.save(property);
    }

    public Optional<Employee> findEmployeeById(Integer id) {
        return employeeRepository.findById(id);
    }

    public Optional<Location> findLocationById(Integer locationId) {
        return locationRepository.findById(locationId);
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public void deleteProperty(Integer id) {
        Optional<Property> propertyOpt = propertyRepository.findById(id);
        if (propertyOpt.isPresent()) {
            propertyRepository.deleteById(id);
        } else {
            throw new RuntimeException("Property not found with id: " + id);
        }
    }
}
