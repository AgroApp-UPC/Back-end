package com.example.agroapp.task.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

/**
 * Resource for updating an existing task.
 * This is what clients send when updating a task.
 */
public record UpdateTaskResource(

        @NotNull(message = "Task date is required")
        Date date,

        @NotBlank(message = "Name is required")
        @Size(max = 200, message = "Name must not exceed 200 characters")
        String name,

        @NotBlank(message = "Description is required")
        @Size(max = 500, message = "Description must not exceed 500 characters")
        String description
) {
}
