package com.trueid.auth.dto;

import java.util.Set;

public class AuthResponse {

    private String token;
    private String type = "Bearer";
    private String username;
    private Set<String> roles;
    private String message;

    public AuthResponse(String token, String username, Set<String> roles, String message) {
        this.token = token;
        this.username = username;
        this.roles = roles;
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public String getMessage() {
        return message;
    }
}
