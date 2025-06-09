package com.tfg.backend.controller;

import com.tfg.backend.dto.PropertyDTO;
import com.tfg.backend.entity.Employee;
import com.tfg.backend.entity.Image;
import com.tfg.backend.entity.Property;
import com.tfg.backend.service.PropertyService;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tfg.backend.entity.Location;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping("/sale")
    public List<Property> getPropertiesForSale() {
        return propertyService.getPropertiesForSale();
    }

    @GetMapping("/rent")
    public List<Property> getPropertiesForRent() {
        return propertyService.getPropertiesForRent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Integer id) {
        Optional<Property> property = propertyService.getPropertyById(id);
        return property.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/new")
    public ResponseEntity<Property> createProperty(@RequestBody PropertyDTO dto) {
        Optional<Employee> employeeOpt = propertyService.findEmployeeById(dto.employeeId);
        if (employeeOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Location> locationOpt = propertyService.findLocationById(dto.locationId);
        if (locationOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Property property = new Property();
        property.setTitle(dto.title);
        property.setBedrooms(dto.bedrooms);
        property.setBathrooms(dto.bathrooms);
        property.setParking(dto.parking);
        property.setPool(dto.pool);
        property.setSqmt(dto.sqmt);
        property.setFloor(dto.floor);
        property.setElevator(dto.elevator);
        property.setPrice(dto.price);

        property.setPublishDate(LocalDate.now());

        try {
            Property.PropertyType type = Property.PropertyType.valueOf(dto.type.toUpperCase());
            property.setType(type);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

        property.setLocation(locationOpt.get());

        property.setEmployee(employeeOpt.get());

        if (dto.imageUrls != null && !dto.imageUrls.isEmpty()) {
            List<Image> images = dto.imageUrls.stream()
                    .map(url -> {
                        Image img = new Image();
                        img.setUrl(url);
                        img.setProperty(property);
                        return img;
                    }).toList();
            property.setImages(images);
        }

        Property savedProperty = propertyService.saveProperty(property);
        return ResponseEntity.ok(savedProperty);
    }

}
