package com.example.agroapp.iam.interfaces.rest.resources;

public record SignInUserResource(
        String email,
        String password
) {
}

