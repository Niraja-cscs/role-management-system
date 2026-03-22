package com.example.rolemanagement.dto;

import java.util.HashSet;
import java.util.Set;

public class UserResponseDTO {

    private Long id;  // ✅ ADD THIS

    private String username;

    private Set<String> roles = new HashSet<>();        // ✅ avoid null
    private Set<String> permissions = new HashSet<>();  // ✅ avoid null

    public UserResponseDTO() {}

    public UserResponseDTO(Long id, String username, Set<String> roles, Set<String> permissions) {
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.permissions = permissions;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Set<String> getRoles() { return roles; }
    public void setRoles(Set<String> roles) { this.roles = roles; }

    public Set<String> getPermissions() { return permissions; }
    public void setPermissions(Set<String> permissions) { this.permissions = permissions; }
}