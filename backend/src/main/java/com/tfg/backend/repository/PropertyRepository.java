package com.tfg.backend.repository;

import com.tfg.backend.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Integer> {

    List<Property> findByTypeOrderByPublishDateDesc(Property.PropertyType type);
    
    @Query("SELECT p FROM Property p WHERE p.location.id = :locationId AND p.type = :type")
    List<Property> findByLocationAndType(
        @Param("locationId") Integer locationId,
        @Param("type") Property.PropertyType type);
    
    boolean existsById(Integer id);
}