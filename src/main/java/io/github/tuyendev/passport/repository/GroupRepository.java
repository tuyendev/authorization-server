package io.github.tuyendev.passport.repository;

import io.github.tuyendev.passport.entity.Group;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import java.math.BigInteger;

public interface GroupRepository extends DataTablesRepository<Group, BigInteger> {
}
