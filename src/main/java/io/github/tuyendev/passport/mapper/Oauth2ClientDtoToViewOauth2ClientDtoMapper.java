package io.github.tuyendev.passport.mapper;

import com.naharoo.commons.mapstruct.BidirectionalMapper;
import io.github.tuyendev.passport.dto.client.Oauth2ClientDto;
import io.github.tuyendev.passport.dto.client.ViewOauth2ClientDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface Oauth2ClientDtoToViewOauth2ClientDtoMapper extends BidirectionalMapper<Oauth2ClientDto, ViewOauth2ClientDto> {
}
