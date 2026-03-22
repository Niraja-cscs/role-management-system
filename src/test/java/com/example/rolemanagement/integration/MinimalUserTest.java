package com.example.rolemanagement.integration;

import com.example.rolemanagement.entity.Role;
import com.example.rolemanagement.entity.User;
import com.example.rolemanagement.repository.UserRepository;
import com.example.rolemanagement.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class MinimalUserTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    void setUp() {
        // Clear all data to avoid primary key conflicts
        userRepository.deleteAll();
        roleRepository.deleteAll();

        // 1. Create Role
        Role adminRole = new Role();
        adminRole.setName("ADMIN");
        roleRepository.save(adminRole);

        // 2. Create User
        User adminUser = new User();
        adminUser.setUsername("admin");
        adminUser.setPassword("123");
        adminUser.setRoles(new HashSet<>(List.of(adminRole)));

        userRepository.save(adminUser);
    }

    @Test
    @Transactional
    void testAdminUserExists() {
        User admin = userRepository.findByUsername("admin")
                .orElseThrow(() -> new RuntimeException("User not found in H2 database!"));

        assertEquals("admin", admin.getUsername());
        assertEquals("123", admin.getPassword());
    }
}