package io.github.tuyendev.passport.service.client;

import com.naharoo.commons.mapstruct.MappingFacade;
import io.github.tuyendev.passport.entity.Oauth2Client;
import io.github.tuyendev.passport.repository.Oauth2ClientRepository;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

@Service
public class Oauth2ClientServiceImpl implements Oauth2ClientService {

    private final Oauth2ClientRepository oauth2ClientRepo;

    private final MappingFacade mapper;

    public Oauth2ClientServiceImpl(Oauth2ClientRepository oauth2ClientRepo, MappingFacade mapper) {
        this.oauth2ClientRepo = oauth2ClientRepo;
        this.mapper = mapper;
    }

    public DataTablesOutput<Oauth2Client> find(DataTablesInput input) {
        return oauth2ClientRepo.findAll(input);
    }
}
