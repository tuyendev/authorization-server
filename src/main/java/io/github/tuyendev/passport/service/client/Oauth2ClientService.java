package io.github.tuyendev.passport.service.client;

import io.github.tuyendev.passport.entity.Oauth2Client;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

public interface Oauth2ClientService {

    DataTablesOutput<Oauth2Client> find(DataTablesInput input);

}
