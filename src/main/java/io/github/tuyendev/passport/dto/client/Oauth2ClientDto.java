package io.github.tuyendev.passport.dto.client;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
public class Oauth2ClientDto implements Serializable {

    private static final long serialVersionUID = 4687988559162263447L;

    protected String id;

    protected String clientId;

    protected String clientName;

    protected String authorizationGrantTypes;

    protected String scopes;

    protected Date createdDate;

    protected Date lastModifiedDate;

    public Oauth2ClientDto() {
    }

    public Oauth2ClientDto(String id, String clientId, String clientName, String authorizationGrantTypes, String scopes, Date createdDate, Date lastModifiedDate) {
        this.id = id;
        this.clientId = clientId;
        this.clientName = clientName;
        this.authorizationGrantTypes = authorizationGrantTypes;
        this.scopes = scopes;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }
}
