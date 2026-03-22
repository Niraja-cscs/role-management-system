package com.example.rolemanagement.controller;

import com.example.rolemanagement.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class TokenController {

    @Autowired
    private JwtUtil jwtUtil;

    // GET API to check token info
    @GetMapping("/check")
    public Map<String, Object> checkToken(@RequestHeader("Authorization") String authHeader) {
        // Expecting header like: "Bearer <token>"
        String token = authHeader.startsWith("Bearer ") ? authHeader.substring(7) : authHeader;

        String username = jwtUtil.extractUsername(token);
        Date issuedAt = jwtUtil.extractIssuedAt(token);
        Date expiration = jwtUtil.extractExpiration(token);

        Map<String, Object> response = new HashMap<>();
        response.put("username", username);
        response.put("issuedAt", issuedAt);
        response.put("expiration", expiration);
        response.put("isExpired", jwtUtil.isTokenExpired(token));

        return response;
    }
}