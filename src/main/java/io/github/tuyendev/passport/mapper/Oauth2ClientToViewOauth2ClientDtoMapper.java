package io.github.tuyendev.passport.mapper;

import com.naharoo.commons.mapstruct.BidirectionalMapper;
import io.github.tuyendev.passport.dto.client.ViewOauth2ClientDto;
import io.github.tuyendev.passport.entity.Oauth2Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface Oauth2ClientToViewOauth2ClientDtoMapper extends BidirectionalMapper<Oauth2Client, ViewOauth2ClientDto> {
    ViewOauth2ClientDto map(Oauth2Client oauth2Client);
}
