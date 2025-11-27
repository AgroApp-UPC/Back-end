package com.example.agroapp.community.interfaces.rest.resources;

import java.time.LocalDateTime;

public record CommunityRecommendationResource(
        Long id,
        String userName,
        LocalDateTime commentDate,
        String comment
) {
}

