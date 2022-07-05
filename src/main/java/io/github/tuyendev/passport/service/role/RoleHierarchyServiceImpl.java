package io.github.tuyendev.passport.service.role;

import io.github.tuyendev.passport.repository.RoleRepository;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RoleHierarchyServiceImpl implements RoleHierarchyService {

    private final RoleHierarchy roleHierarchy;
    private final RoleRepository roleRepo;
    private final Object lock = new Object();

    public RoleHierarchyServiceImpl(RoleHierarchy roleHierarchy, RoleRepository roleRepo) {
        this.roleHierarchy = roleHierarchy;
        this.roleRepo = roleRepo;
    }

    @Override
    public void reload() {
        synchronized (lock) {
            List<String> roleHierarchies = roleRepo.findAllActiveRoles().stream()
                    .map(role -> {
                        if (Objects.isNull(role.getParent())) {
                            return role.getName();
                        }
                        return role.getName() + " > " + role.getParent().getName();
                    }).collect(Collectors.toList());

            ((RoleHierarchyImpl) roleHierarchy).setHierarchy(String.join("\n", roleHierarchies));
        }
    }
}
