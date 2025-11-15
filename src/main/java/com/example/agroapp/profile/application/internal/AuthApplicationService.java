package com.example.agroapp.profile.application.internal;


import com.example.agroapp.profile.domain.model.aggregates.User;
import com.example.agroapp.profile.domain.repositories.UserRepository;
import com.example.agroapp.profile.interfaces.rest.resources.*;
import com.example.agroapp.profile.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import com.example.agroapp.profile.interfaces.rest.transform.UserResourceFromEntityAssembler;
import com.example.agroapp.shared.interfaces.rest.resources.MessageResource;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthApplicationService {

    private final UserRepository userRepository;
    private final ProfileApplicationService profileApplicationService;


    private String generateDummyTokenForUser(User user) {
        return "dummy-token-for-user-" + user.getId();
    }

    public AuthResponse signup(SignupRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("El email ya está registrado.");
        }
        if (userRepository.existsByDni(request.dni())) {
            throw new IllegalArgumentException("El DNI ya está registrado.");
        }

        User.Role role = User.Role.valueOf(request.rol().toUpperCase());

        User user = User.builder()
                .nombre(request.nombre())
                .apellido(request.apellido())
                .email(request.email())
                .password(request.password())
                .dni(request.dni())
                .rol(role)
                .build();

        User saved = userRepository.save(user);
        profileApplicationService.getOrCreateProfileForUser(saved);

        var userResource =
                UserResourceFromEntityAssembler.toResourceFromEntity(saved);

        return new AuthResponse(
                generateDummyTokenForUser(saved),
                userResource
        );
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("Credenciales inválidas."));

        // SIN PASSWORD ENCODER → comparación directa
        if (!request.password().equals(user.getPassword())) {
            throw new IllegalArgumentException("Credenciales inválidas.");
        }

        return new AuthResponse(
                generateDummyTokenForUser(user),
                UserResourceFromEntityAssembler.toResourceFromEntity(user)
        );
    }

    public UserResource getMe(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));
        return UserResourceFromEntityAssembler.toResourceFromEntity(user);
    }
}
