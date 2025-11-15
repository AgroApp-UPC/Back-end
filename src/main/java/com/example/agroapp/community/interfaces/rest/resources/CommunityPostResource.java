package com.example.agroapp.community.interfaces.rest.resources;

import java.util.Date;

/**
 * Resource representation of a CommunityPost for API responses
 * This is what clients will receive when they request community posts
 */
public record CommunityPostResource(
        Long id,
        String user,
        String description,
        Date createdAt,
        Date updatedAt
) {
}
