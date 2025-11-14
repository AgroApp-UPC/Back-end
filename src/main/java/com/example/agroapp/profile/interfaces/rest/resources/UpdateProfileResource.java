package com.example.agroapp.profile.interfaces.rest.resources;

public record UpdateProfileResource(
        String avatarUrl,
        String language,
        Boolean notificationsEnabled,
        Boolean alertsEnabled
) {}