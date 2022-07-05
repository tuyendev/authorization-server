package io.github.tuyendev.passport.dto.role;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Builder
@Data
public class AddRoleRequestDto implements Serializable {

    private static final long serialVersionUID = -7309911264950986502L;

    private Long parentId;

    @NotEmpty(message = "{app.role.adding.validation.name}")
    private String name;

    private String description;
}
