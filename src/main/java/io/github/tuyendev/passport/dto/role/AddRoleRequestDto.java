package io.github.tuyendev.passport.dto.role;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
public class AddRoleRequestDto extends RoleDto {
    private Long parentId;
}
