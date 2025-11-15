package com.example.agroapp.field.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

/**
 * Resource for updating an existing Field.
 * This is what clients send when updating a field.
 */
public record UpdateFieldResource(

        @NotBlank(message = "Name is required")
        @Size(max = 200, message = "Name must not exceed 200 characters")
        String name,

        @Size(max = 500, message = "Image URL must not exceed 500 characters")
        String imageUrl,

        @NotBlank(message = "Product is required")
        @Size(max = 100, message = "Product must not exceed 100 characters")
        String product,

        @NotBlank(message = "Location is required")
        @Size(max = 100, message = "Location must not exceed 100 characters")
        String location,

        @NotBlank(message = "Field size is required")
        @Size(max = 50, message = "Field size must not exceed 50 characters")
        String fieldSize,

        @NotBlank(message = "Crop is required")
        @Size(max = 100, message = "Crop must not exceed 100 characters")
        String crop,

        @NotNull(message = "Days since planting is required")
        Integer daysSincePlanting,

        @NotNull(message = "Planting date is required")
        Date plantingDate,

        @NotNull(message = "Expected harvest date is required")
        Date expectedHarvestDate,

        @NotBlank(message = "Soil type is required")
        @Size(max = 100, message = "Soil type must not exceed 100 characters")
        String soilType,

        @NotBlank(message = "Watering info is required")
        @Size(max = 100, message = "Watering info must not exceed 100 characters")
        String watering,

        @NotBlank(message = "Sunlight info is required")
        @Size(max = 100, message = "Sunlight info must not exceed 100 characters")
        String sunlight,

        @NotBlank(message = "Status is required")
        @Size(max = 50, message = "Status must not exceed 50 characters")
        String status
) { }
