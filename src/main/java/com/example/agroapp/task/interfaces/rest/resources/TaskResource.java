package com.example.agroapp.task.interfaces.rest.resources;

import java.util.Date;

/**
 * Resource representation of a Task for API responses.
 * This is what clients receive when requesting task data.
 */
public record TaskResource(
        Long id,
        Long fieldId,
        Date date,
        String name,
        String description,
        boolean completed,
        Date createdAt,
        Date updatedAt
) {
}
