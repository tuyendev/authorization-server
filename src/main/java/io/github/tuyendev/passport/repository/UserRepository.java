package io.github.tuyendev.passport.repository;

import io.github.tuyendev.passport.entity.User;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface UserRepository extends DataTablesRepository<User, BigInteger> {

    Optional<User> findUserByEmail(String email);
}
