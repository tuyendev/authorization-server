package io.github.tuyendev.passport.repository;

import io.github.tuyendev.passport.entity.Oauth2Authorization;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface Oauth2AuthorizationRepository extends DataTablesRepository<Oauth2Authorization, String> {
}
