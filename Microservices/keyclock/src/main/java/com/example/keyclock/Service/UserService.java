package com.example.keyclock.Service;

import com.example.keyclock.Models.UserRecord;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface UserService {

    void createUser(UserRecord newUserRecord);
    void deleteUser(String userId);
    UserResource getUser(String userId);
    UserRepresentation getUserDetails(String userId);
//    List<RoleRepresentation> getUserRoles(String userId);
}