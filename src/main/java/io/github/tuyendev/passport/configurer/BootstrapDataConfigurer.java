package io.github.tuyendev.passport.configurer;

import io.github.tuyendev.passport.repository.AuthorityRepository;
import io.github.tuyendev.passport.repository.UserRepository;
import io.github.tuyendev.passport.utils.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Configuration
@DependsOn(value = {AuthorizationServiceConfigurer.BEAN_ID,
        AuthenticationProviderConfigurer.BEAN_ID})
@Slf4j(topic = "CONFIGURER")
public class BootstrapDataConfigurer {

    private static final String passwordDirectory = "/etc/passport";

    private final String defaultClientId;

    private final String defaultAdminUser;

    private final RegisteredClientRepository registeredClientRepo;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepo;

    private final AuthorityRepository userAuthorityRepo;

    public BootstrapDataConfigurer(@Value("${app.default.client-id}") String defaultClientId,
                                   @Value("${app.default.admin-user}") String defaultAdminUser,
                                   RegisteredClientRepository registeredClientRepo,
                                   PasswordEncoder passwordEncoder, UserRepository userRepo, AuthorityRepository userAuthorityRepo) {
        this.defaultClientId = defaultClientId;
        this.defaultAdminUser = defaultAdminUser;
        this.registeredClientRepo = registeredClientRepo;
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.userAuthorityRepo = userAuthorityRepo;
    }

    private static boolean exportPassword(char[] password, String fileName) {
        File secureFolder = new File(passwordDirectory);
        secureFolder.mkdirs();
        try (FileWriter fileWriter = new FileWriter(passwordDirectory + "/" + fileName);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            writer.write(String.valueOf(password));
        } catch (IOException e) {
            throw new RuntimeException("Cannot export password to file", e);
        }
        return true;
    }

    @PostConstruct
    @Transactional
    public void init() {
        initialDefaultClient();
        initialDefaultAdminUser();
    }

    private void initialDefaultAdminUser() {
//        Optional<User> existed = userRepo.findUserByUsername(defaultAdminUser);
//        if (existed.isPresent()) {
//            log.debug(String.format("Default admin user [ %s ] is created", defaultAdminUser));
//            return;
//        }
//        char[] initialPassword = PasswordUtil.generatePassword(32);
//        exportPassword(initialPassword, "initial-admin-password");
//        User user = User.builder()
//                .email("passport@bssd.vn")
//                .emailVerified(true)
//                .username(defaultAdminUser)
//                .preferredUsername(defaultAdminUser)
//                .name(defaultAdminUser)
//                .unsignedName(defaultAdminUser)
//                .password(passwordEncoder.encode(String.valueOf(initialPassword)))
//                .enabled(true)
//                .credsExpired(false)
//                .accExpired(false)
//                .accLocked(false)
//                .build();
//        userRepo.save(user);
//        UserAuthority authority = UserAuthority.builder()
//                .username(defaultAdminUser)
//                .authority("ADMIN")
//                .build();
//        userAuthorityRepo.save(authority);
        log.debug("Success initial default app admin");
    }

    private void initialDefaultClient() {

        RegisteredClient defaultClient = registeredClientRepo.findByClientId(defaultClientId);
        if (Objects.nonNull(defaultClient)) {
            log.debug(String.format("Default client [ %s ] is created", defaultClientId));
            return;
        }

        char[] initialPassword = PasswordUtil.generatePassword(32);
        exportPassword(initialPassword, "initial-client-id-password");

        defaultClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId(defaultClientId)
                .clientSecret(passwordEncoder.encode(String.valueOf(initialPassword)))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("https://oidcdebugger.com/debug")
                .scope(OidcScopes.OPENID)
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(false).build())
                .build();
        registeredClientRepo.save(defaultClient);
        log.debug("Success initial default app client-id");
    }

}
