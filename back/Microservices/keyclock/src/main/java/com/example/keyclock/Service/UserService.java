package com.example.keyclock.Service;

import com.example.keyclock.Models.UserRecord;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;

import java.util.List;

public interface UserService {

   void createUser(UserRecord newUserRecord);
//    void sendVerificationEmail(String userId);
    void deleteUser(String userId);
//    void forgotPassword(String username);
     UserResource getUser(String userId);
//    List<RoleRepresentation> getUserRoles(String userId);
//    List<GroupRepresentation> getUserGroups(String userId);
}

