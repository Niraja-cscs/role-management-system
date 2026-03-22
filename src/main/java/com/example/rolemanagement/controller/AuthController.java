package com.example.rolemanagement.controller;

import com.example.rolemanagement.config.JwtUtil;
import com.example.rolemanagement.dto.AuthRequest;
import com.example.rolemanagement.dto.AuthResponse;
import com.example.rolemanagement.entity.Role;
import com.example.rolemanagement.entity.User;
import com.example.rolemanagement.service.UserService;
import com.example.roleframework.exception.InvalidUserException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        // ✅ Get user by username
        User user = userService.getByUsername(request.getUsername());

        // ✅ Validate password
        if (!user.getPassword().equals(request.getPassword())) {
            throw new InvalidUserException("Invalid password");
        }

        // ✅ Extract roles
        List<String> roles = user.getRoles()
                .stream()
                .map(Role::getName)
                .toList();

        // ✅ Generate JWT token
        String token = jwtUtil.generateToken(user.getUsername(), roles);

        // ✅ Dates for issuedAt and expiration
        Date now = new Date();
        Date exp = new Date(System.currentTimeMillis() + 86400000); // 24h

        return new AuthResponse(token, now, exp, user.getUsername());
    }
}