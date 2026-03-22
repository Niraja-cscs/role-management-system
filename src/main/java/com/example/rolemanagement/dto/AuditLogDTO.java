package com.example.rolemanagement.dto;



public class AuditLogDTO {
    private Long id;
    private String action;
    private String username;
    private String timestamp;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}