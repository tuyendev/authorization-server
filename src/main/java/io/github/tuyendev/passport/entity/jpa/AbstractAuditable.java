package io.github.tuyendev.passport.entity.jpa;

import org.springframework.lang.Nullable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractAuditable<U extends Serializable> implements Serializable {

    protected @Nullable U createdBy;

    @Temporal(TemporalType.TIMESTAMP) //
    protected @Nullable Date createdDate;

    protected @Nullable U lastModifiedBy;

    @Temporal(TemporalType.TIMESTAMP) //
    protected @Nullable Date lastModifiedDate;

    public U getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(U createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return null == createdDate ? null
                : LocalDateTime.ofInstant(createdDate.toInstant(), ZoneId.systemDefault());
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = Date.from(createdDate.atZone(ZoneId.systemDefault()).toInstant());
    }

    public U getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(U lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public LocalDateTime getLastModifiedDate() {
        return null == lastModifiedDate ? null
                : LocalDateTime.ofInstant(lastModifiedDate.toInstant(), ZoneId.systemDefault());
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = Date.from(lastModifiedDate.atZone(ZoneId.systemDefault()).toInstant());
    }
}
