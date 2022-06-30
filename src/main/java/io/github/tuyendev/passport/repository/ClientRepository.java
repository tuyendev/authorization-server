package io.github.tuyendev.passport.repository;

import io.github.tuyendev.passport.entity.Oauth2Client;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface ClientRepository extends DataTablesRepository<Oauth2Client, String> {
}
