package com.example.rolemanagement.controller;

import com.example.rolemanagement.entity.Permission;
import com.example.rolemanagement.entity.Role;
import com.example.rolemanagement.service.PermissionService;
import com.example.rolemanagement.service.RoleService;
import com.example.roleframework.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;
    private final PermissionService permissionService;

    public RoleController(RoleService roleService, PermissionService permissionService) {
        this.roleService = roleService;
        this.permissionService = permissionService;
    }

    // CREATE
    @PostMapping
    public Role create(@RequestBody Role role) {
        return roleService.save(role);
    }

    // READ ALL
    @GetMapping
    public List<Role> getAll() {
        return roleService.getAll();
    }

    // READ ONE
    @GetMapping("/{id}")
    public Role getById(@PathVariable Long id) {
        return roleService.getById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Role update(@PathVariable Long id, @RequestBody Role updated) {
        Role role = roleService.getById(id);
        role.setName(updated.getName());
        return roleService.save(role);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        roleService.delete(id);
        return "Role deleted";
    }

    // 🔥 ASSIGN PERMISSIONS TO ROLE
    @PutMapping("/{roleId}/permissions")
    public Role assignPermissions(@PathVariable Long roleId,
                                  @RequestBody List<Long> permissionIds) {

        Role role = roleService.getById(roleId);

        Set<Permission> permissions = permissionIds.stream()
                .map(pid -> permissionService.getById(pid))
                .collect(Collectors.toSet());

        role.setPermissions(permissions);

        return roleService.save(role);
    }
}