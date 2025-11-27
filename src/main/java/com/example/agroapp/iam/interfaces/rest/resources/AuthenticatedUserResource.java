package com.example.agroapp.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(
        Long id,
        String userName,
        String email,
        String token
) {
}

