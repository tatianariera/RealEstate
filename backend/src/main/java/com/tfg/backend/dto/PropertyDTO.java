package com.tfg.backend.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class PropertyDTO {
    public String title;
    public Integer locationId;
    public Double price;
    public Integer sqmt;
    public Integer bedrooms;
    public Integer bathrooms;
    public String floor;
    public Boolean parking;
    public Boolean pool;
    public Boolean elevator;
    public String type;
    public List<String> imageUrls;
    public Integer employeeId;
}
