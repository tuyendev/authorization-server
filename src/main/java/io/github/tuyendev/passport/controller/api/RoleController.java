package io.github.tuyendev.passport.controller.api;

import io.github.tuyendev.passport.dto.role.AddRoleRequestDto;
import io.github.tuyendev.passport.dto.role.RoleDto;
import io.github.tuyendev.passport.service.role.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<RoleDto> testAddRole(@Valid @RequestBody AddRoleRequestDto request) {
        RoleDto result = roleService.create(request.getParentId(), request.getName());
        return ResponseEntity.ok(result);
    }
}
