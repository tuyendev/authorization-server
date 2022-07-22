package io.github.tuyendev.passport.mapper;

import com.naharoo.commons.mapstruct.BidirectionalMapper;
import io.github.tuyendev.passport.dto.user.UserDto;
import io.github.tuyendev.passport.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoToUserMapper extends BidirectionalMapper<User, UserDto> {
}
