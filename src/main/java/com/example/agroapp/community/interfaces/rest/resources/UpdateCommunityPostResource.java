package com.example.agroapp.community.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Resource for updating an existing community post
 * This is what clients send when updating a post
 */
public record UpdateCommunityPostResource(
        @NotBlank(message = "Description is required")
        @Size(max = 1000, message = "Description must not exceed 1000 characters")
        String description
) {
}
