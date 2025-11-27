package com.example.agroapp.iam.interfaces.rest.resources;

public record UpdateUserProfileResource(
        String userName,
        String email,
        String phoneNumber
) {
}

