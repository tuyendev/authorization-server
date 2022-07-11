package io.github.tuyendev.passport.dto.client;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class ViewOauth2ClientDto extends Oauth2ClientDto {
    private String actions;

    public ViewOauth2ClientDto() {
    }

    @Builder(builderMethodName = "viewBuilder")
    public ViewOauth2ClientDto(String id, String clientId, String clientName, String authorizationGrantTypes, String scopes, Date createdDate, Date lastModifiedDate) {
        super(id, clientId, clientName, authorizationGrantTypes, scopes, createdDate, lastModifiedDate);
        this.actions = id;
    }
}
