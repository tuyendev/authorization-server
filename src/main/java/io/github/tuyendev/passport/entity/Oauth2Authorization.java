package io.github.tuyendev.passport.entity;

import io.github.tuyendev.passport.entity.jpa.AbstractAuditable;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "oauth2_authorization")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Oauth2Authorization extends AbstractAuditable<String> {
    @Id
    @Column
    private String id;

    @Column(name = "registered_client_Id")
    private String registeredClientId;

    @Column(name = "principal_name")
    private String principalName;

    @Column(name = "authorization_grant_type")
    private String authorizationGrantType;

    @Column(name = "attributes", columnDefinition = "TEXT")
    private String attributes;

    @Column(name = "state", length = 500)
    private String state;

    @Column(name = "authorization_code_value", columnDefinition = "TEXT")
    private String authorizationCodeValue;

    @Column(name = "authorization_code_issued_at")
    private Instant authorizationCodeIssuedAt;

    @Column(name = "authorization_code_expires_at")
    private Instant authorizationCodeExpiresAt;

    @Column(name = "authorization_code_metadata")
    private String authorizationCodeMetadata;

    @Column(name = "access_token_value", columnDefinition = "TEXT")
    private String accessTokenValue;

    @Column(name = "access_token_issued_at")
    private Instant accessTokenIssuedAt;

    @Column(name = "access_token_expires_at")
    private Instant accessTokenExpiresAt;

    @Column(name = "access_token_metadata", columnDefinition = "TEXT")
    private String accessTokenMetadata;

    @Column(name = "access_token_type")
    private String accessTokenType;

    @Column(name = "access_token_scopes", columnDefinition = "TEXT")
    private String accessTokenScopes;

    @Column(name = "refresh_token_value", length = 4000)
    private String refreshTokenValue;

    @Column(name = "refresh_token_issued_at")
    private Instant refreshTokenIssuedAt;

    @Column(name = "refresh_token_expires_at")
    private Instant refreshTokenExpiresAt;

    @Column(name = "refresh_token_metadata", columnDefinition = "TEXT")
    private String refreshTokenMetadata;

    @Column(name = "oidc_id_token_value", columnDefinition = "TEXT")
    private String oidcIdTokenValue;

    @Column(name = "oidc_id_token_issued_at")
    private Instant oidcIdTokenIssuedAt;

    @Column(name = "oidc_id_token_expires_at")
    private Instant oidcIdTokenExpiresAt;

    @Column(name = "oidc_id_token_metadata", columnDefinition = "TEXT")
    private String oidcIdTokenMetadata;

    @Column(name = "oidc_id_token_claims", columnDefinition = "TEXT")
    private String oidcIdTokenClaims;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Oauth2Authorization)) return false;
        Oauth2Authorization that = (Oauth2Authorization) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return "Authorization{" +
                "id='" + id + '\'' +
                ", registeredClientId='" + registeredClientId + '\'' +
                ", principalName='" + principalName + '\'' +
                ", authorizationGrantType='" + authorizationGrantType + '\'' +
                ", attributes='" + attributes + '\'' +
                ", state='" + state + '\'' +
                ", authorizationCodeValue='" + "ENCRYPTED" + '\'' +
                ", authorizationCodeIssuedAt=" + authorizationCodeIssuedAt +
                ", authorizationCodeExpiresAt=" + authorizationCodeExpiresAt +
                ", authorizationCodeMetadata='" + authorizationCodeMetadata + '\'' +
                ", accessTokenValue='" + "ENCRYPTED" + '\'' +
                ", accessTokenIssuedAt=" + accessTokenIssuedAt +
                ", accessTokenExpiresAt=" + accessTokenExpiresAt +
                ", accessTokenMetadata='" + accessTokenMetadata + '\'' +
                ", accessTokenType='" + accessTokenType + '\'' +
                ", accessTokenScopes='" + accessTokenScopes + '\'' +
                ", refreshTokenValue='" + "ENCRYPTED" + '\'' +
                ", refreshTokenIssuedAt=" + refreshTokenIssuedAt +
                ", refreshTokenExpiresAt=" + refreshTokenExpiresAt +
                ", refreshTokenMetadata='" + refreshTokenMetadata + '\'' +
                ", oidcIdTokenValue='" + "ENCRYPTED" + '\'' +
                ", oidcIdTokenIssuedAt=" + oidcIdTokenIssuedAt +
                ", oidcIdTokenExpiresAt=" + oidcIdTokenExpiresAt +
                ", oidcIdTokenMetadata='" + oidcIdTokenMetadata + '\'' +
                ", oidcIdTokenClaims='" + oidcIdTokenClaims + '\'' +
                ", createdBy=" + createdBy +
                ", createdDate=" + createdDate +
                ", lastModifiedBy=" + lastModifiedBy +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
