package io.github.tuyendev.passport.dto.user;

import io.github.tuyendev.passport.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseDto {
    private static final long serialVersionUID = -5303974641207878496L;
    private String email;
    private Boolean emailVerified;
    private String username;
    private String preferredUsername;

    private String password;
    private String name;
    private String unsignedName;
    private String givenName;
    private String middleName;
    private String familyName;
    private String nickname;
    private String profile;
    private String picture;
    private String website;
    private Integer gender;
    private LocalDate birthdate;
    private String zoneInfo;
    private String locale;
    private String phoneNumber;
    private Boolean phoneNumberVerified;
    private Boolean enabled;
    private Boolean accLocked;
    private Boolean accExpired;
    private Boolean credsExpired;

    @Override
    public String toString() {
        return "UserDto{" +
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
                '}';
    }
}
