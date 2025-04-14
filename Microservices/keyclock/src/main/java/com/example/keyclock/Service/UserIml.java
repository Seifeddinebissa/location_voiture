package com.example.keyclock.Service;

import com.example.keyclock.Models.UserRecord;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserIml implements UserService {

    @Value("${app.keycloak.realm}")
    private String realm;
    private final Keycloak keycloak;

    @Override
    public void createUser(UserRecord newUserRecord) {
        UsersResource usersResource = getUsersResource();

        // Check for duplicate username
        List<UserRepresentation> existingUsers = usersResource.search(newUserRecord.username(), null, null, null, null, null);
        if (existingUsers.stream().anyMatch(user -> user.getUsername().equalsIgnoreCase(newUserRecord.username()))) {
            throw new IllegalArgumentException("Username already exists: " + newUserRecord.username());
        }

        // Check for duplicate email
        existingUsers = usersResource.search(null, null, null, newUserRecord.email(), null, null);
        if (existingUsers.stream().anyMatch(user -> user.getEmail().equalsIgnoreCase(newUserRecord.email()))) {
            throw new IllegalArgumentException("Email already exists: " + newUserRecord.email());
        }

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEnabled(true);
        userRepresentation.setFirstName(newUserRecord.firstName());
        userRepresentation.setLastName(newUserRecord.lastName());
        userRepresentation.setUsername(newUserRecord.username());
        userRepresentation.setEmail(newUserRecord.email());
        userRepresentation.setEmailVerified(true);

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setValue(newUserRecord.password());
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setTemporary(false);

        userRepresentation.setCredentials(List.of(credentialRepresentation));

        Response response = usersResource.create(userRepresentation);

        if (!Objects.equals(201, response.getStatus())) {
            throw new RuntimeException("Failed to create user: Status code " + response.getStatus());
        }

        log.info("New user created: {}", newUserRecord.username());
    }

    @Override
    public void deleteUser(String userId) {
        UsersResource usersResource = getUsersResource();
        Response response = usersResource.delete(userId);
        if (!Objects.equals(204, response.getStatus())) {
            throw new RuntimeException("Failed to delete user: Status code " + response.getStatus());
        }
        log.info("User deleted: {}", userId);
    }

    @Override
    public UserResource getUser(String userId) {
        UsersResource usersResource = getUsersResource();
        return usersResource.get(userId);
    }

    @Override
    public UserRepresentation getUserDetails(String userId) {
        UserResource userResource = getUsersResource().get(userId);
        UserRepresentation userRepresentation = userResource.toRepresentation();
        if (userRepresentation == null) {
            throw new RuntimeException("User not found: " + userId);
        }
        return userRepresentation;
    }

    private UsersResource getUsersResource() {
        return keycloak.realm(realm).users();
    }
}