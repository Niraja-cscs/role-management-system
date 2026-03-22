package com.example.rolemanagement.service;

import com.example.roleframework.exception.InvalidUserException;
import com.example.roleframework.exception.ResourceNotFoundException;
import com.example.rolemanagement.dto.UserRequestDTO;
import com.example.rolemanagement.dto.UserResponseDTO;
import com.example.rolemanagement.entity.Permission;
import com.example.rolemanagement.entity.Role;
import com.example.rolemanagement.entity.User;
import com.example.rolemanagement.repository.RoleRepository;
import com.example.rolemanagement.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuditLogService auditLogService;

    // 🔥 CREATE USER
    public UserResponseDTO createUser(UserRequestDTO dto) {

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());

        Set<Role> roles = new HashSet<>(roleRepository.findAllById(dto.getRoleIds()));

        if (roles.isEmpty()) {
            throw new RuntimeException("Invalid roles");
        }

        user.setRoles(roles);

        User saved = repo.save(user);

        // ✅ AUDIT LOG ADDED
        auditLogService.log(
                "CREATE_USER",
                saved.getUsername(),
                "New user created"
        );

        return convertToDTO(saved);
    }

    // 🔥 UPDATE USER
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {

        User user = getById(id);

        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());

        Set<Role> roles = new HashSet<>(roleRepository.findAllById(dto.getRoleIds()));

        if (!roles.isEmpty()) {
            user.setRoles(roles);
        }

        User saved = repo.save(user);

        // ✅ AUDIT LOG ADDED
        auditLogService.log(
                "UPDATE_USER",
                saved.getUsername(),
                "User updated"
        );

        return convertToDTO(saved);
    }

    public User save(User user) {
        return repo.save(user);
    }

    public List<User> getAll() {
        return repo.findAll();
    }

    public User getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    // 🔥 DELETE USER
    public void delete(Long id) {
        User user = getById(id);

        repo.deleteById(id);

        // ✅ AUDIT LOG ADDED
        auditLogService.log(
                "DELETE_USER",
                user.getUsername(),
                "User deleted"
        );
    }

    public User getByUsername(String username) {
        return repo.findByUsername(username)
                .orElseThrow(() -> new InvalidUserException("User not found"));
    }

    public Page<User> getAllUsers(Pageable pageable) {
        return repo.findAll(pageable);
    }

    // 🔥 DTO MAPPING
    public UserResponseDTO convertToDTO(User user) {

        Set<String> roles = Optional.ofNullable(user.getRoles())
                .orElse(Collections.emptySet())
                .stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        Set<String> permissions = Optional.ofNullable(user.getRoles())
                .orElse(Collections.emptySet())
                .stream()
                .flatMap(role -> Optional.ofNullable(role.getPermissions())
                        .orElse(Collections.emptySet())
                        .stream())
                .map(Permission::getName)
                .collect(Collectors.toSet());

        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                roles,
                permissions
        );
    }
}