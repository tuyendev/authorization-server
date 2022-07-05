package io.github.tuyendev.passport.service.role;

import io.github.tuyendev.passport.dto.role.RoleDto;

public interface RoleService {
    RoleDto create(Long parentId, String name, String desc);
}
