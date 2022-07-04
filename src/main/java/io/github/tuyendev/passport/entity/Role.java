package io.github.tuyendev.passport.entity;

import io.github.tuyendev.passport.entity.jpa.AbstractPersistable;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Role extends AbstractPersistable<String, Long> {

    public static String ADMIN_ROLE = "ADMIN";

    @Column(length = 100, unique = true)
    private String name;

    @Column(length = 500)
    private String description;

    private Integer status;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private Role parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private Set<Role> children;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private Set<User> users;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "roles_authorities",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "authority_id", referencedColumnName = "id"))
    private Set<Authority> authorities;

}
