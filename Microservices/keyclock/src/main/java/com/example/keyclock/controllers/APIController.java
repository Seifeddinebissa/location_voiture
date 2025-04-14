package com.example.keyclock.controllers;

import com.example.keyclock.Models.UserRecord;
import com.example.keyclock.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class APIController {

    private final UserService userService;
    private final Keycloak keycloak;

    @Value("${app.keycloak.realm}")
    private String realm;

    @Value("${app.keycloak.serverUrl}")
    private String keycloakServerUrl;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRecord newUserRecord) {
        try {
            userService.createUser(newUserRecord);
            List<UserRepresentation> users = keycloak.realm(realm).users()
                    .search(newUserRecord.username(), null, null, null, null, null);
            String userId = users.stream()
                    .filter(user -> user.getUsername().equals(newUserRecord.username()))
                    .findFirst()
                    .map(UserRepresentation::getId)
                    .orElseThrow(() -> new RuntimeException("User not found after creation"));
            return ResponseEntity.status(HttpStatus.CREATED).body(userId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id) {
        try {
            UserRepresentation user = userService.getUserDetails(id);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete user: " + e.getMessage());
        }
    }

    @GetMapping("/login")
    public ResponseEntity<?> login() {
        return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT)
                .header("Location", "/auth/oauth2/authorization/keycloak")
                .build();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        SecurityContextHolder.clearContext();

        String keycloakLogoutUrl = String.format("%s/realms/%s/protocol/openid-connect/logout?" +
                        "client_id=my-app&post_logout_redirect_uri=%s",
                keycloakServerUrl, realm, "http://localhost:8082/auth");
        return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT)
                .header("Location", keycloakLogoutUrl)
                .build();
    }

    @GetMapping("/home")
    public ResponseEntity<?> home() {
        return ResponseEntity.ok("Welcome to the application");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred: " + e.getMessage());
    }
}