package io.github.tuyendev.passport.repository;

import io.github.tuyendev.passport.constants.Status;
import io.github.tuyendev.passport.entity.Role;
import io.github.tuyendev.passport.exception.NotFoundEntityException;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends DataTablesRepository<Role, Long> {

    Optional<Role> findRoleByNameAndStatus(String name, Integer status);

    default Role getAdmin() {
        return findAdmin().orElseThrow(() -> new NotFoundEntityException("app.role.exception.admin-not-existed"));
    }

    default Optional<Role> findAdmin() {
        return findActiveRoleByName(Role.ADMIN_ROLE);
    }

    default Optional<Role> findActiveRoleByName(String name) {
        return findRoleByNameAndStatus(name, Status.ACTIVE);
    }

    Set<Role> findAllByStatus(Integer status);

    default Set<Role> findAllActiveRoles() {
        return findAllByStatus(Status.ACTIVE);
    }
}
