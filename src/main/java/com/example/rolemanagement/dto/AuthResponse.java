package com.example.rolemanagement.dto;

import java.util.Date;

public class AuthResponse {

    private String token;
    private Date issuedAt;
    private Date expiration;
    private String username;

    public AuthResponse(String token, Date issuedAt, Date expiration, String username) {
        this.token = token;
        this.issuedAt = issuedAt;
        this.expiration = expiration;
        this.username = username;
    }

    public String getToken() { return token; }
    public Date getIssuedAt() { return issuedAt; }
    public Date getExpiration() { return expiration; }
    public String getUsername() { return username; }
}