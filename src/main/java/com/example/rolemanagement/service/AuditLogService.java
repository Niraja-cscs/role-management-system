package com.example.rolemanagement.service;

import com.example.rolemanagement.entity.AuditLog;
import com.example.rolemanagement.repository.AuditLogRepository;
import com.example.roleframework.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditLogService {

    @Autowired
    private AuditLogRepository repo;

    // ✅ CREATE LOG
    public void log(String action, String username, String details) {
        AuditLog log = new AuditLog();
        log.setAction(action);
        log.setUsername(username);
        log.setDetails(details);

        repo.save(log);
    }

    // ✅ GET ALL
    public List<AuditLog> getAll() {
        return repo.findAll();
    }

    // ✅ GET BY ID (BEST PRACTICE)
    public AuditLog getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Audit log not found with id: " + id));
    }
}