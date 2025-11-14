package com.example.agroapp.profile.interfaces.rest.resources;

public record AuthResponse(
        String token,
        UserResource user
) {}