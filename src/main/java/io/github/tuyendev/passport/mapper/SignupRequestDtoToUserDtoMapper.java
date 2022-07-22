package io.github.tuyendev.passport.mapper;

import com.naharoo.commons.mapstruct.UnidirectionalMapper;
import io.github.tuyendev.passport.dto.signup.SignupRequestDto;
import io.github.tuyendev.passport.dto.user.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SignupRequestDtoToUserDtoMapper extends UnidirectionalMapper<SignupRequestDto, UserDto> {
}
