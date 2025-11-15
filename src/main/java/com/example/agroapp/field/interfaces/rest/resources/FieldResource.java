package com.example.agroapp.field.interfaces.rest.resources;

import java.util.Date;

/**
 * Resource representation of a Field for API responses.
 * This is what clients will receive when they request field data.
 */
public record FieldResource(
        Long id,
        String name,
        String imageUrl,
        String product,
        String location,
        String fieldSize,
        String crop,
        Integer daysSincePlanting,
        Date plantingDate,
        Date expectedHarvestDate,
        String soilType,
        String watering,
        String sunlight,
        String status,
        Date createdAt,
        Date updatedAt
) {
}
