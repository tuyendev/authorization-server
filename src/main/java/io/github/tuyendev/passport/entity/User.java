package io.github.tuyendev.passport.entity;

import io.github.tuyendev.passport.entity.jpa.AbstractPersistable;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;


@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends AbstractPersistable<String, Long> {

    @Column(name = "email", length = 80, unique = true, nullable = false)
    private String email;

    @Column(name = "email_verified")
    private Boolean emailVerified;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "preferred_username")
    private String preferredUsername;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "unsigned_name")
    private String unsignedName;

    @Column(name = "given_name")
    private String givenName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "family_name")
    private String familyName;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "profile")
    private String profile;

    @Column(name = "picture", length = 1000)
    private String picture;

    @Column(name = "website", length = 200)
    private String website;

    @Column(name = "gender", length = 1)
    private Integer gender;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "zone_info")
    private String zoneInfo;

    @Column(name = "locale")
    private String locale;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "phone_number_verified")
    private Boolean phoneNumberVerified;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "acc_locked")
    private Boolean accLocked;

    @Column(name = "acc_expired")
    private Boolean accExpired;

    @Column(name = "creds_expired")
    private Boolean credsExpired;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "group_members",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Collection<Group> groups;

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", emailVerified=" + emailVerified +
                ", username='" + username + '\'' +
                ", preferredUsername='" + preferredUsername + '\'' +
                ", password='" + "ENCRYPTED" + '\'' +
                ", name='" + name + '\'' +
                ", unsignedName='" + unsignedName + '\'' +
                ", givenName='" + givenName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", profile='" + profile + '\'' +
                ", picture='" + picture + '\'' +
                ", website='" + website + '\'' +
                ", gender=" + gender +
                ", birthdate=" + birthdate +
                ", zoneInfo='" + zoneInfo + '\'' +
                ", locale='" + locale + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", phoneNumberVerified=" + phoneNumberVerified +
                ", enabled=" + enabled +
                ", accLocked=" + accLocked +
                ", accExpired=" + accExpired +
                ", credsExpired=" + credsExpired +
                ", roles=" + roles +
                ", id=" + id +
                ", createdBy=" + createdBy +
                ", createdDate=" + createdDate +
                ", lastModifiedBy=" + lastModifiedBy +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
