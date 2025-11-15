package com.example.agroapp.profile.interfaces.rest.resources;

public record LoginRequest(
        String email,
        String password
) {}