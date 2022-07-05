package io.github.tuyendev.passport.entity;

import io.github.tuyendev.passport.entity.jpa.AbstractPersistable;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "groups")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Group extends AbstractPersistable<String, Long> {

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "domain", length = 100)
    private String domain;

    @Column(name = "email", length = 80)
    private String email;

    private Integer status;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "groups")
    private Set<User> users;
}
