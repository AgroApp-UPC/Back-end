package com.example.agroapp.iam.interfaces.rest.resources;

public record UserResource(
        Long id,
        String userName,
        String email,
        String phoneNumber,
        String identificator
) {
}

