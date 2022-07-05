package io.github.tuyendev.passport.dto.user;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class AddUserRequestDto implements Serializable {
    private static final long serialVersionUID = 7884966649798089264L;
}
