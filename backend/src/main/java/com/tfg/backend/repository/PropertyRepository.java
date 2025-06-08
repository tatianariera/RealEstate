package com.tfg.backend.repository;

import com.tfg.backend.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> findByTypeOrderByPublishDateDesc(Property.PropertyType type);

}
