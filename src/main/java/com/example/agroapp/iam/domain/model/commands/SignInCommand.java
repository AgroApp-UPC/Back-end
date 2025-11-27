package com.example.agroapp.iam.domain.model.commands;

public record SignInCommand(
        String email,
        String password
) {
}

