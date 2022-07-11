package io.github.tuyendev.passport.dto.client;


import java.io.Serializable;
import java.util.Date;


public class Oauth2ClientDto implements Serializable {

    private static final long serialVersionUID = 4687988559162263447L;

    private String id;

    private String clientId;

    private String clientName;

    private String scopes;

    private Date createdDate;

    private Date lastModifiedDate;

}
