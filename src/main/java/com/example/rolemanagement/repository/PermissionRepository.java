package com.example.rolemanagement.repository;

import com.example.rolemanagement.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}