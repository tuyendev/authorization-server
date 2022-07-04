package io.github.tuyendev.passport.configurer;

import io.github.tuyendev.passport.repository.UserRepository;
import io.github.tuyendev.passport.security.JpaUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration(value = AuthenticationProviderConfigurer.BEAN_ID)
public class AuthenticationProviderConfigurer {

    public static final String BEAN_ID = "Passport@AuthenticationProviderConfigurer@000";

    @Bean
    PasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());
        encoders.put("argon2", new Argon2PasswordEncoder());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        return new DelegatingPasswordEncoder("bcrypt", encoders);
    }

    @Bean
    UserDetailsService userDetailsService(UserRepository userRepo) {
        return new JpaUserDetailsService(userRepo);
    }
}
