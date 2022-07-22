package io.github.tuyendev.passport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseDto implements Serializable {
    protected String createdBy;
    protected Date createdDate;
    protected String lastModifiedBy;
    protected Date lastModifiedDate;
}
