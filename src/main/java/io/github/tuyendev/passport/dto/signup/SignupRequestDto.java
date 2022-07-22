package io.github.tuyendev.passport.dto.signup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupRequestDto implements Serializable {
    private static final long serialVersionUID = 798485372237085630L;

    private String email;

    private String givenName;

    private String familyName;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date birthdate;

    private String gender;

    private String password;

    private String repassword;

}
