package com.example.agroapp.cropfield.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

/**
 * Resource for updating an existing crop field
 * This is what clients send when updating a crop field
 */
public record UpdateCropFieldResource(
        @NotBlank(message = "Title is required")
        @Size(max = 100, message = "Title must not exceed 100 characters")
        String title,

        @NotNull(message = "Days is required")
        Integer days,

        @NotNull(message = "Planting date is required")
        Date plantingDate,

        @NotNull(message = "Harvest date is required")
        Date harvestDate,

        @NotBlank(message = "Field location is required")
        @Size(max = 200, message = "Field location must not exceed 200 characters")
        String field,

        @NotBlank(message = "Status is required")
        @Size(max = 50, message = "Status must not exceed 50 characters")
        String status
) {
}
