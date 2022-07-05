package io.github.tuyendev.passport.service.role;

import com.naharoo.commons.mapstruct.MappingFacade;
import io.github.tuyendev.passport.constants.Status;
import io.github.tuyendev.passport.dto.role.RoleDto;
import io.github.tuyendev.passport.entity.Role;
import io.github.tuyendev.passport.exception.NotFoundEntityException;
import io.github.tuyendev.passport.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepo;

    private final RoleHierarchyService roleHierarchyService;

    private final MappingFacade mapper;

    public RoleServiceImpl(RoleRepository roleRepo, RoleHierarchyService roleHierarchyService, MappingFacade mapper) {
        this.roleRepo = roleRepo;
        this.roleHierarchyService = roleHierarchyService;
        this.mapper = mapper;
    }

    @Override
    public RoleDto create(Long parentId, String name, String desc) {
        Role parent = getParentRole(parentId);
        Role role = Role.builder()
                .parent(parent)
                .name(name)
                .description(desc)
                .status(Status.ACTIVE)
                .build();

        Role savedRole = roleRepo.save(role);
        roleHierarchyService.reload();
        return mapper.map(savedRole, RoleDto.class);
    }

    private Role getParentRole(Long parentId) {
        if (Objects.isNull(parentId)) {
            return roleRepo.getAdmin();
        }

        return roleRepo.findById(parentId)
                .orElseThrow(() -> new NotFoundEntityException("app.role.exception.parent-not-existed"));
    }
}
