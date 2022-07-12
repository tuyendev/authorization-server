package io.github.tuyendev.passport.service.client;

import com.naharoo.commons.mapstruct.MappingFacade;
import io.github.tuyendev.passport.dto.client.ViewOauth2ClientDto;
import io.github.tuyendev.passport.repository.Oauth2ClientRepository;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Oauth2ClientServiceImpl implements Oauth2ClientService {

    private final Oauth2ClientRepository oauth2ClientRepo;

    private final MappingFacade mapper;

    public Oauth2ClientServiceImpl(Oauth2ClientRepository oauth2ClientRepo, MappingFacade mapper) {
        this.oauth2ClientRepo = oauth2ClientRepo;
        this.mapper = mapper;
    }

    public DataTablesOutput<ViewOauth2ClientDto> find(DataTablesInput input) {
        var data = oauth2ClientRepo.findAll(input);
        DataTablesOutput<ViewOauth2ClientDto> result = new DataTablesOutput<>();
        result.setData(mapper.mapAsList(data.getData(), ViewOauth2ClientDto.class));
        result.setDraw(data.getDraw());
        result.setError(data.getError());
        result.setRecordsFiltered(data.getRecordsFiltered());
        result.setRecordsTotal(data.getRecordsTotal());
        result.setSearchPanes(data.getSearchPanes());
        return result;
    }

    @Override
    public void delete(List<String> ids) {
        oauth2ClientRepo.deleteAllById(ids);
    }
}
