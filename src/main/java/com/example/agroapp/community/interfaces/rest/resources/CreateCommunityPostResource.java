package com.example.agroapp.community.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Resource for creating a new community post
 * This is what clients send when creating a post
 */
public record CreateCommunityPostResource(
        @NotBlank(message = "User name is required")
        @Size(max = 100, message = "User name must not exceed 100 characters")
        String user,

        @NotBlank(message = "Description is required")
        @Size(max = 1000, message = "Description must not exceed 1000 characters")
        String description
) {
}
