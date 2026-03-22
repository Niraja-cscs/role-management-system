package com.example.rolemanagement.repository;

import com.example.rolemanagement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}