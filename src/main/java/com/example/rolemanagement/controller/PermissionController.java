package com.example.rolemanagement.controller;

import com.example.rolemanagement.entity.Permission;
import com.example.rolemanagement.service.PermissionService;
import com.example.roleframework.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    private final PermissionService service;

    public PermissionController(PermissionService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public Permission create(@RequestBody Permission permission) {
        return service.save(permission);
    }

    // READ ALL
    @GetMapping
    public List<Permission> getAll() {
        return service.getAll();
    }

    // READ ONE
    @GetMapping("/{id}")
    public Permission getById(@PathVariable Long id) {
        return service.getAll().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found"));
    }

    // UPDATE
    @PutMapping("/{id}")
    public Permission update(@PathVariable Long id, @RequestBody Permission updated) {
        Permission p = getById(id);
        p.setName(updated.getName());
        return service.save(p);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        Permission p = getById(id);
        service.delete(p.getId());
        return "Permission deleted";
    }
}