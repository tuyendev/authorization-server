package io.github.tuyendev.passport.controller.api;

import io.github.tuyendev.passport.dto.client.ViewOauth2ClientDto;
import io.github.tuyendev.passport.service.client.Oauth2ClientService;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final Oauth2ClientService oauth2ClientService;

    public ClientController(Oauth2ClientService oauth2ClientService) {
        this.oauth2ClientService = oauth2ClientService;
    }

    @PostMapping(value = "/list")
    public DataTablesOutput<ViewOauth2ClientDto> list(@Valid @RequestBody DataTablesInput input) {
        return oauth2ClientService.find(input);
    }

}
