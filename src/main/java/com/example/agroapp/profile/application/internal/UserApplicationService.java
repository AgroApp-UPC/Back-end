package com.example.agroapp.profile.application.internal;

import com.example.agroapp.profile.domain.model.aggregates.User;
import com.example.agroapp.profile.domain.repositories.UserRepository;
import com.example.agroapp.profile.interfaces.rest.resources.UpdateUserResource;
import com.example.agroapp.profile.interfaces.rest.resources.UserResource;
import com.example.agroapp.profile.interfaces.rest.resources.SignupRequest;

import com.example.agroapp.profile.interfaces.rest.transform.UserResourceFromEntityAssembler;
import com.example.agroapp.profile.application.internal.ProfileApplicationService;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserApplicationService {

    private final UserRepository userRepository;
    private final ProfileApplicationService profileApplicationService;

    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));
    }

    public User signup(SignupRequest resource) {

        if (userRepository.existsByEmail(resource.email())) {
            throw new IllegalArgumentException("El email ya está registrado.");
        }
        if (userRepository.existsByDni(resource.dni())) {
            throw new IllegalArgumentException("El DNI ya está registrado.");
        }

        User user = new User(
                resource.nombre(),
                resource.apellido(),
                resource.email(),
                resource.password(),
                resource.dni(),
                User.Role.valueOf(resource.rol())
        );

        return userRepository.save(user);
    }


    public UserResource getUserResourceByEmail(String email) {
        return UserResourceFromEntityAssembler.toResourceFromEntity(getByEmail(email));
    }

    public UserResource updateUser(String email, UpdateUserResource resource) {
        User user = getByEmail(email);

        if (resource.nombre() != null) user.setNombre(resource.nombre());
        if (resource.apellido() != null) user.setApellido(resource.apellido());
        if (resource.email() != null) user.setEmail(resource.email());
        if (resource.dni() != null) user.setDni(resource.dni());


        if (resource.newPassword() != null && !resource.newPassword().isBlank()) {
            user.setPassword(resource.newPassword());
        }

        User updated = userRepository.save(user);
        return UserResourceFromEntityAssembler.toResourceFromEntity(updated);
    }

    public void deleteUser(String email) {
        User user = getByEmail(email);
        userRepository.delete(user);
    }
}