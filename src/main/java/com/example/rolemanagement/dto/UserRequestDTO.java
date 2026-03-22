package com.example.rolemanagement.dto;

import java.util.List;

public class UserRequestDTO {

    private String username;
    private String password;
    private List<Long> roleIds;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public List<Long> getRoleIds() { return roleIds; }
    public void setRoleIds(List<Long> roleIds) { this.roleIds = roleIds; }
}
