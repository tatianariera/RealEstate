package com.tfg.backend.dto;

import java.util.List;
import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class PropertyDTO {
    @NotBlank
    private String title;

    @NotNull
    private Integer locationId;

    @NotNull
    @DecimalMin("0.0")
    private Double price;

    @NotNull
    @Min(0)
    private Double sqmt;  // Cambiado a Double para consistencia con entidad

    @NotNull
    @Min(0)
    private Integer bedrooms;

    @NotNull
    @Min(0)
    private Integer bathrooms;

    @NotBlank
    private String floor;

    @NotNull
    private Boolean parking;

    @NotNull
    private Boolean pool;

    @NotNull
    private Boolean elevator;

    @NotBlank
    private String type;

    private List<String> imageUrls;

    @NotNull
    private Integer employeeId;
}
