package io.github.tuyendev.passport.repository;

import io.github.tuyendev.passport.entity.Authority;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface UserAuthorityRepository extends CrudRepository<Authority, BigInteger> {
}
