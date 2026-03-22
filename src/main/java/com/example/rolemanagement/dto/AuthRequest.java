package com.example.rolemanagement.dto;

public class AuthRequest {
    private String username;
    private String password;

    // Default constructor (needed for @RequestBody)
    public AuthRequest() {}

    // Constructor
    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter & Setter
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}