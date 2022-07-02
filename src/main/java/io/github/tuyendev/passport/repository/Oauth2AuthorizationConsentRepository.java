package io.github.tuyendev.passport.repository;

import io.github.tuyendev.passport.entity.Oauth2AuthorizationConsent;
import org.springframework.data.repository.CrudRepository;

public interface Oauth2AuthorizationConsentRepository extends CrudRepository<Oauth2AuthorizationConsent, String> {
}
