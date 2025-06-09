package com.tfg.backend.service;

import com.tfg.backend.entity.Employee;
import com.tfg.backend.entity.Property;
import com.tfg.backend.repository.EmployeeRepository;
import com.tfg.backend.repository.PropertyRepository;
import com.tfg.backend.entity.Location;
import com.tfg.backend.repository.LocationRepository;

import org.springframework.beans.factory.annotation.Autowired;
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

    public Optional<Property> getPropertyById(Integer id) {
        return propertyRepository.findById(id);
    }

    public Property saveProperty(Property property) {
        return propertyRepository.save(property);
    }

    @Autowired
    private EmployeeRepository employeeRepository;

    public Optional<Employee> findEmployeeById(Integer id) {
        return employeeRepository.findById(id);
    }

    @Autowired
    private LocationRepository locationRepository;
    public Optional<Location> findLocationById(Integer locationId) {
        return locationRepository.findById(locationId);
    }



}
