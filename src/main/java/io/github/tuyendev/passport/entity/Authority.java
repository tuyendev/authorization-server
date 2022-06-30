package io.github.tuyendev.passport.entity;

import io.github.tuyendev.passport.entity.jpa.AbstractPersistable;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "authorities")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Authority extends AbstractPersistable<String, Long> {

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @ManyToMany(mappedBy = "authorities")
    private Collection<Role> roles;

    @Override
    public String toString() {
        return "Authority{" +
                "authority='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", createdBy=" + createdBy +
                ", createdDate=" + createdDate +
                ", lastModifiedBy=" + lastModifiedBy +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
