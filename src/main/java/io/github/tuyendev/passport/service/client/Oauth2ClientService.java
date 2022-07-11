package io.github.tuyendev.passport.service.client;

import io.github.tuyendev.passport.dto.client.ViewOauth2ClientDto;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

public interface Oauth2ClientService {

    DataTablesOutput<ViewOauth2ClientDto> find(DataTablesInput input);

}
