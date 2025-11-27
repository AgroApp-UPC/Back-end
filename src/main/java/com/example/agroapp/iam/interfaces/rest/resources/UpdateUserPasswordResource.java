package com.example.agroapp.iam.interfaces.rest.resources;

public record UpdateUserPasswordResource(
        String currentPassword,
        String newPassword
) {
}

