package com.example.rolemanagement.service;

import com.example.rolemanagement.entity.Role;
import com.example.rolemanagement.repository.RoleRepository;
import com.example.roleframework.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repo;

    public Role save(Role role) { return repo.save(role); }

    public List<Role> getAll() { return repo.findAll(); }

    public Role getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}