package com.example.agroapp.cropfield.interfaces.rest.resources;

import java.util.Date;

/**
 * Resource representation of a CropField for API responses
 * This is what clients will receive when they request crop fields
 */
public record CropFieldResource(
        Long id,
        String title,
        Integer days,
        Date plantingDate,
        Date harvestDate,
        String field,
        String status,
        Date createdAt,
        Date updatedAt
) {
}
