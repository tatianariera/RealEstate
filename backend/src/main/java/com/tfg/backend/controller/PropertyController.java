package com.tfg.backend.controller;

import com.tfg.backend.dto.PropertyDTO;
import com.tfg.backend.entity.Employee;
import com.tfg.backend.entity.Image;
import com.tfg.backend.entity.Property;
import com.tfg.backend.entity.Location;
import com.tfg.backend.service.PropertyService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> createProperty(@Valid @RequestBody PropertyDTO dto) {
        Optional<Employee> employeeOpt = propertyService.findEmployeeById(dto.getEmployeeId());
        if (employeeOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Employee not found");
        }

        Optional<Location> locationOpt = propertyService.findLocationById(dto.getLocationId());
        if (locationOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Location not found");
        }

        Property property = dtoToEntity(dto, employeeOpt.get(), locationOpt.get());
        Property savedProperty = propertyService.saveProperty(property);
        return ResponseEntity.ok(savedProperty);
    }

    @GetMapping("")
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

@PutMapping("/{id}")
public ResponseEntity<?> updateProperty(@PathVariable Integer id, @Valid @RequestBody PropertyDTO dto) {
    Optional<Property> existingPropertyOpt = propertyService.getPropertyById(id);
    if (existingPropertyOpt.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    Optional<Employee> employeeOpt = propertyService.findEmployeeById(dto.getEmployeeId());
    if (employeeOpt.isEmpty()) {
        return ResponseEntity.badRequest().body("Employee not found");
    }

    Optional<Location> locationOpt = propertyService.findLocationById(dto.getLocationId());
    if (locationOpt.isEmpty()) {
        return ResponseEntity.badRequest().body("Location not found");
    }

    Property property = existingPropertyOpt.get();

    // Actualizamos campos básicos
    property.setTitle(dto.getTitle());
    property.setBedrooms(dto.getBedrooms());
    property.setBathrooms(dto.getBathrooms());
    property.setParking(dto.getParking());
    property.setPool(dto.getPool());
    property.setSqmt(dto.getSqmt());
    property.setFloor(dto.getFloor());
    property.setElevator(dto.getElevator());
    property.setPrice(dto.getPrice());

    try {
        Property.PropertyType type = Property.PropertyType.valueOf(dto.getType().toUpperCase());
        property.setType(type);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().body("Invalid property type");
    }

    property.setLocation(locationOpt.get());
    property.setEmployee(employeeOpt.get());

    // Manejo especial de las imágenes
    if (dto.getImageUrls() != null) {
        // 1. Limpiar la colección existente de manera segura
        if (property.getImages() != null) {
            property.getImages().clear(); // Esto solo funciona si la colección es mutable
        } else {
            property.setImages(new ArrayList<>());
        }

        // 2. Agregar las nuevas imágenes
        for (String url : dto.getImageUrls()) {
            if (url != null && !url.trim().isEmpty()) {
                Image img = new Image();
                img.setUrl(url.trim());
                img.setProperty(property);
                property.getImages().add(img);
            }
        }
    }

    Property updatedProperty = propertyService.saveProperty(property);
    return ResponseEntity.ok(updatedProperty);
}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Integer id) {
        try {
            propertyService.deleteProperty(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Método auxiliar para convertir DTO a entidad Property
    private Property dtoToEntity(PropertyDTO dto, Employee employee, Location location) {
        Property property = new Property();
        property.setTitle(dto.getTitle());
        property.setBedrooms(dto.getBedrooms());
        property.setBathrooms(dto.getBathrooms());
        property.setParking(dto.getParking());
        property.setPool(dto.getPool());
        property.setSqmt(dto.getSqmt());
        property.setFloor(dto.getFloor());
        property.setElevator(dto.getElevator());
        property.setPrice(dto.getPrice());
        property.setPublishDate(LocalDate.now());

        try {
            Property.PropertyType type = Property.PropertyType.valueOf(dto.getType().toUpperCase());
            property.setType(type);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid property type");
        }

        property.setLocation(location);
        property.setEmployee(employee);

        if (dto.getImageUrls() != null && !dto.getImageUrls().isEmpty()) {
            List<Image> images = dto.getImageUrls().stream()
                    .map(url -> {
                        Image img = new Image();
                        img.setUrl(url);
                        img.setProperty(property);
                        return img;
                    }).toList();
            property.setImages(images);
        }
        return property;
    }
}
