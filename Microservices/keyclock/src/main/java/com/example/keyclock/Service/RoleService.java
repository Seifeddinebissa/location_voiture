package com.example.keyclock.Service;

public interface RoleService {

    void assignRole(String userId ,String roleName);
    void deleteRoleFromUser(String userId ,String roleName);

}

