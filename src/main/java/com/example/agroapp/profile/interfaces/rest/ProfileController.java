package com.example.agroapp.profile.interfaces.rest;

import com.example.agroapp.profile.application.internal.AuthApplicationService;
import com.example.agroapp.profile.application.internal.ProfileApplicationService;
import com.example.agroapp.profile.application.internal.UserApplicationService;
import com.example.agroapp.profile.interfaces.rest.resources.*;
import com.example.agroapp.shared.interfaces.rest.resources.MessageResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final AuthApplicationService authApplicationService;
    private final UserApplicationService userApplicationService;
    private final ProfileApplicationService profileApplicationService;

    // --------------------------------------------------------------------
    // AUTH
    // --------------------------------------------------------------------

    @PostMapping("/auth/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody SignupRequest request) {
        return ResponseEntity.ok(authApplicationService.signup(request));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authApplicationService.login(request));
    }

    @GetMapping("/auth/me")
    public ResponseEntity<UserResource> getMe(@RequestParam String email) {
        return ResponseEntity.ok(authApplicationService.getMe(email));
    }

    // --------------------------------------------------------------------
    // USER
    // --------------------------------------------------------------------

    @GetMapping("/user/{email}")
    public ResponseEntity<UserResource> getUser(@PathVariable String email) {
        return ResponseEntity.ok(userApplicationService.getUserResourceByEmail(email));
    }

    @PutMapping("/user/{email}")
    public ResponseEntity<UserResource> updateUser(
            @PathVariable String email,
            @RequestBody UpdateUserResource resource) {

        return ResponseEntity.ok(userApplicationService.updateUser(email, resource));
    }

    @DeleteMapping("/user/{email}")
    public ResponseEntity<MessageResource> deleteUser(@PathVariable String email) {
        userApplicationService.deleteUser(email);
        return ResponseEntity.ok(new MessageResource("Usuario eliminado correctamente."));
    }

    // --------------------------------------------------------------------
    // PROFILE
    // --------------------------------------------------------------------

    @GetMapping("/{email}")
    public ResponseEntity<ProfileResource> getProfile(@PathVariable String email) {
        return ResponseEntity.ok(profileApplicationService.getProfile(email));
    }

    @PutMapping("/{email}")
    public ResponseEntity<ProfileResource> updateProfile(
            @PathVariable String email,
            @RequestBody UpdateProfileResource resource) {

        return ResponseEntity.ok(profileApplicationService.updateProfile(email, resource));
    }

    // --------------------------------------------------------------------
    // SETTINGS
    // --------------------------------------------------------------------

    @GetMapping("/settings/{email}")
    public ResponseEntity<ProfileSettingsResource> getSettings(@PathVariable String email) {
        return ResponseEntity.ok(profileApplicationService.getSettings(email));
    }

    @PutMapping("/settings/reset/{email}")
    public ResponseEntity<MessageResource> resetSettings(@PathVariable String email) {
        profileApplicationService.resetProfileSettings(email);
        return ResponseEntity.ok(new MessageResource("Configuraciones restablecidas."));
    }

}