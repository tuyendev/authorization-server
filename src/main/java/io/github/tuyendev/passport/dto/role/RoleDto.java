package io.github.tuyendev.passport.dto.role;


import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class RoleDto {
    protected String name;
    protected String description;
    protected Integer status;
}
