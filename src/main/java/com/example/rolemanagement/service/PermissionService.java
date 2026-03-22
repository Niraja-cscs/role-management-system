package com.example.rolemanagement.service;

import com.example.rolemanagement.entity.Permission;
import com.example.rolemanagement.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository repo;

    public Permission save(Permission p) {
        return repo.save(p);
    }

    public List<Permission> getAll() {
        return repo.findAll();
    }
    public Permission getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found"));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}