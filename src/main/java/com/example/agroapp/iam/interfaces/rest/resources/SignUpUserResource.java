package com.example.agroapp.iam.interfaces.rest.resources;

public record SignUpUserResource(
        String userName,
        String email,
        String phoneNumber,
        String identificator,
        String password
) {
}

