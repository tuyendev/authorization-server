package io.github.tuyendev.passport.repository;

import io.github.tuyendev.passport.constants.Status;
import io.github.tuyendev.passport.entity.Authority;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.Optional;
import java.util.Set;

public interface AuthorityRepository extends CrudRepository<Authority, BigInteger> {

    Optional<Authority> findAuthorityByNameAndStatus(String name, Integer status);

    default Optional<Authority> findAuthorityActiveByName(String name) {
        return findAuthorityByNameAndStatus(name, Status.ACTIVE);
    }

    Set<Authority> findAllByStatus(Integer status);

    default Set<Authority> findAllActiveAuthorities() {
        return findAllByStatus(Status.ACTIVE);
    }
}
