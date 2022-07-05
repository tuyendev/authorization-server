package io.github.tuyendev.passport.dto.role;


import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
public class RoleDto implements Serializable {

    private static final long serialVersionUID = -9184785550146601125L;

    protected String name;

    protected String description;

    protected Integer status;
}
