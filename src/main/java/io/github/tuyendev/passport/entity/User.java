package io.github.tuyendev.passport.entity;

import io.github.tuyendev.passport.entity.jpa.AbstractPersistable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "group_members",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<Group> groups;

    private User() {
    }

    public User(String email, Boolean emailVerified, String username, String preferredUsername, String password, String name, String unsignedName, String givenName, String middleName, String familyName, String nickname, String profile, String picture, String website, Integer gender, LocalDate birthdate, String zoneInfo, String locale, String phoneNumber, Boolean phoneNumberVerified, Boolean enabled, Boolean accLocked, Boolean accExpired, Boolean credsExpired, Set<Role> roles, Set<Group> groups) {
        this.email = email;
        this.emailVerified = Objects.nonNull(emailVerified) ? emailVerified : Boolean.FALSE;
        this.username = username;
        this.preferredUsername = preferredUsername;
        this.password = password;
        this.name = name;
        this.unsignedName = unsignedName;
        this.givenName = givenName;
        this.middleName = middleName;
        this.familyName = familyName;
        this.nickname = nickname;
        this.profile = profile;
        this.picture = picture;
        this.website = website;
        this.gender = gender;
        this.birthdate = birthdate;
        this.zoneInfo = zoneInfo;
        this.locale = locale;
        this.phoneNumber = phoneNumber;
        this.phoneNumberVerified = Objects.nonNull(phoneNumberVerified) ? phoneNumberVerified : Boolean.FALSE;
        this.enabled = Objects.nonNull(enabled) ? enabled : Boolean.FALSE;
        this.accLocked = Objects.nonNull(accLocked) ? accLocked : Boolean.FALSE;
        this.accExpired = Objects.nonNull(accExpired) ? accExpired : Boolean.FALSE;
        this.credsExpired = Objects.nonNull(credsExpired) ? credsExpired : Boolean.FALSE;
        this.roles = roles;
        this.groups = groups;
    }

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

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = Objects.nonNull(emailVerified) ? emailVerified : Boolean.FALSE;
    }

    public void setPhoneNumberVerified(Boolean phoneNumberVerified) {
        this.phoneNumberVerified = Objects.nonNull(phoneNumberVerified) ? phoneNumberVerified : Boolean.FALSE;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = Objects.nonNull(enabled) ? enabled : Boolean.FALSE;
    }

    public void setAccLocked(Boolean accLocked) {
        this.accLocked = Objects.nonNull(accLocked) ? accLocked : Boolean.FALSE;
    }

    public void setAccExpired(Boolean accExpired) {
        this.accExpired = Objects.nonNull(accExpired) ? accExpired : Boolean.FALSE;
    }

    public void setCredsExpired(Boolean credsExpired) {
        this.credsExpired = Objects.nonNull(credsExpired) ? accExpired : Boolean.FALSE;
    }

}
