package io.github.tuyendev.passport.entity.jpa;

import org.springframework.data.util.ProxyUtils;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractPersistable<U extends Serializable, PK extends Serializable> extends AbstractAuditable<U> {

    private static final long serialVersionUID = 5217154417797843638L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    protected @Nullable PK id;

    @Nullable
    public PK getId() {
        return id;
    }

    protected void setId(@Nullable PK id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Entity of type %s with id: %s", this.getClass().getName(), getId());
    }


    @Override
    public boolean equals(Object obj) {

        if (null == obj) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!getClass().equals(ProxyUtils.getUserClass(obj))) {
            return false;
        }

        org.springframework.data.jpa.domain.AbstractPersistable<?> that = (org.springframework.data.jpa.domain.AbstractPersistable<?>) obj;

        return Objects.equals(id, that.getId());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        int hashCode = 17;

        hashCode += null == getId() ? 0 : getId().hashCode() * 31;

        return hashCode;
    }
}
