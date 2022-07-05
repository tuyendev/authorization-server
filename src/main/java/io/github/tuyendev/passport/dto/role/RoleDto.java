package io.github.tuyendev.passport.dto.role;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class RoleDto implements Serializable {

    private static final long serialVersionUID = -9184785550146601125L;

    protected String name;

    protected String description;

    protected Integer status;
}
