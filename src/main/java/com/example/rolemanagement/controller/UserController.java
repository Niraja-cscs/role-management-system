package com.example.rolemanagement.controller;

import com.example.rolemanagement.dto.UserRequestDTO;
import com.example.rolemanagement.dto.UserResponseDTO;
import com.example.rolemanagement.entity.Role;
import com.example.rolemanagement.entity.User;
import com.example.rolemanagement.service.RoleService;
import com.example.rolemanagement.service.UserService;
import com.example.roleframework.dto.PageResponse;
import com.example.roleframework.util.PaginationUtil;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    // 🔥 FIXED CREATE (USES DTO)
    @PostMapping
    public UserResponseDTO create(@RequestBody UserRequestDTO dto) {
        return userService.createUser(dto);
    }

    // 🔥 PAGINATION
    @GetMapping
    public PageResponse<UserResponseDTO> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "100") int size) {

        Pageable pageable = PageRequest.of(page - 1, size);
        var usersPage = userService.getAllUsers(pageable);
        var dtoPage = usersPage.map(userService::convertToDTO);

        return PaginationUtil.buildPageResponse(dtoPage);
    }

    @GetMapping("/{id}")
    public UserResponseDTO getById(@PathVariable Long id) {
        return userService.convertToDTO(userService.getById(id));
    }

    // 🔥 FIXED UPDATE (USES DTO)
    @PutMapping("/{id}")
    public UserResponseDTO update(@PathVariable Long id,
                                  @RequestBody UserRequestDTO dto) {
        return userService.updateUser(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        userService.delete(id);
        return "User deleted";
    }

    // assign roles (you can keep this)
    @PutMapping("/{userId}/roles")
    public UserResponseDTO assignRoles(@PathVariable Long userId,
                                       @RequestBody List<Long> roleIds) {

        User user = userService.getById(userId);

        Set<Role> roles = roleIds.stream()
                .map(roleService::getById)
                .collect(Collectors.toSet());

        user.setRoles(roles);
        User saved = userService.save(user);

        return userService.convertToDTO(saved);
    }
}