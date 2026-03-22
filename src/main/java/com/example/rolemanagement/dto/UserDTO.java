package com.example.rolemanagement.dto;

import java.util.Set;

public class UserDTO {
    private Long id;
    private String username;
    private Set<Long> roleIds;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Set<Long> getRoleIds() { return roleIds; }
    public void setRoleIds(Set<Long> roleIds) { this.roleIds = roleIds; }
}