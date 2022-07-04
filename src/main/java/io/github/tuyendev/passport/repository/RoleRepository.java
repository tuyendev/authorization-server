package io.github.tuyendev.passport.repository;

import io.github.tuyendev.passport.constants.Status;
import io.github.tuyendev.passport.entity.Role;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends DataTablesRepository<Role, Long> {

    Optional<Role> findRoleByNameAndStatus(String name, Integer status);

    default Optional<Role> getAdmin() {
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
