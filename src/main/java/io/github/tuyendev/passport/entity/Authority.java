package io.github.tuyendev.passport.entity;

import io.github.tuyendev.passport.entity.jpa.AbstractPersistable;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "authorities")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Authority extends AbstractPersistable<String, Long> {

    public static final String READ = "READ";

    public static final String WRITE = "WRITE";

    public static final String READ_PRIVILEGE = "READ_PRIVILEGE";

    public static final String WRITE_PRIVILEGE = "WRITE_PRIVILEGE";

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    private Integer status;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private Set<Role> roles;

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
