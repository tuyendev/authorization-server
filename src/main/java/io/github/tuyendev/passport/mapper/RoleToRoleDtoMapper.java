package io.github.tuyendev.passport.mapper;

import com.naharoo.commons.mapstruct.BidirectionalMapper;
import io.github.tuyendev.passport.dto.role.RoleDto;
import io.github.tuyendev.passport.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleToRoleDtoMapper extends BidirectionalMapper<Role, RoleDto> {
}
