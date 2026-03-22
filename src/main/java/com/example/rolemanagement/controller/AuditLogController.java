package com.example.rolemanagement.controller;

import com.example.rolemanagement.entity.AuditLog;
import com.example.rolemanagement.service.AuditLogService;
import com.example.roleframework.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/audit-logs")
public class AuditLogController {

    private final AuditLogService auditLogService;

    public AuditLogController(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    // ✅ CREATE LOG (POST)
    @PostMapping
    public String createAuditLog(@RequestBody AuditLog log) {
        auditLogService.log(
                log.getAction(),
                log.getUsername(),
                log.getDetails()
        );
        return "Audit log created successfully";
    }

    // ✅ GET ALL LOGS
    @GetMapping
    public List<AuditLog> getAllAuditLogs() {
        return auditLogService.getAll();
    }

    // ✅ GET BY ID (BETTER WAY)
    @GetMapping("/{id}")
    public AuditLog getAuditLogById(@PathVariable Long id) {
        return auditLogService.getById(id);
    }
}