package io.github.tuyendev.passport.configurer;

import io.github.tuyendev.passport.constants.Status;
import io.github.tuyendev.passport.entity.Authority;
import io.github.tuyendev.passport.entity.Role;
import io.github.tuyendev.passport.entity.User;
import io.github.tuyendev.passport.exception.WriteFileException;
import io.github.tuyendev.passport.repository.AuthorityRepository;
import io.github.tuyendev.passport.repository.RoleRepository;
import io.github.tuyendev.passport.repository.UserRepository;
import io.github.tuyendev.passport.utils.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@DependsOn(value = {AuthorizationServiceConfigurer.BEAN_ID,
        AuthenticationProviderConfigurer.BEAN_ID})
@Slf4j(topic = "CONFIGURER")
public class BootstrapDataConfigurer {

    private final String passwordDirectory;
    private final String defaultAdminUser;

    private final UserRepository userRepo;

    private final RoleRepository roleRepo;

    private final AuthorityRepository authorityRepo;

    private final PasswordEncoder passwordEncoder;

    public BootstrapDataConfigurer(@Value("${app.default.password-directory}") String passwordDirectory,
                                   @Value("${app.default.admin-user}") String defaultAdminUser,
                                   UserRepository userRepo,
                                   RoleRepository roleRepo,
                                   AuthorityRepository authorityRepo,
                                   PasswordEncoder passwordEncoder) {
        this.passwordDirectory = passwordDirectory;
        this.defaultAdminUser = defaultAdminUser;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.authorityRepo = authorityRepo;
        this.passwordEncoder = passwordEncoder;
    }

    private boolean exportPassword(char[] password, String fileName) {
        File secureFolder = new File(passwordDirectory);
        secureFolder.mkdirs();
        try (FileWriter fileWriter = new FileWriter(passwordDirectory + "/" + fileName);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            writer.write(String.valueOf(password));
        } catch (IOException e) {
            throw new WriteFileException("Cannot export password to file", e);
        }
        return true;
    }


    @EventListener(value = ApplicationReadyEvent.class)
    @Transactional
    public void init() {
        initialDefaultAuthorities();
        initialDefaultAdminRole();
        initialDefaultMemberRole();
        initialDefaultAdminUser();
    }

    private void initialDefaultAdminRole() {
        Optional<Role> adminRole = roleRepo.findAdmin();
        if (adminRole.isPresent()) {
            log.debug("Default admin role is created");
            return;
        }
        Set<Authority> authorities = authorityRepo.findAllActiveAuthorities();
        Role admin = Role.builder()
                .name(Role.ADMIN_ROLE)
                .description("Administrator")
                .status(Status.ACTIVE)
                .authorities(authorities)
                .build();
        roleRepo.save(admin);
        log.debug("Success initial admin role");
    }

    private void initialDefaultMemberRole() {
        Optional<Role> memberRole = roleRepo.findActiveRoleByName(Role.MEMBER_ROLE);
        if (memberRole.isPresent()) {
            log.debug("Default member role is created");
            return;
        }

        Role admin = roleRepo.getAdmin();
        Set<Authority> authorities = authorityRepo.findAllActiveAuthorities()
                .stream().filter(authority -> authority.getName().equals(Authority.READ) || authority.getName().equals(Authority.WRITE))
                .collect(Collectors.toSet());
        Role member = Role.builder()
                .name(Role.MEMBER_ROLE)
                .parent(admin)
                .description("Member")
                .status(Status.ACTIVE)
                .authorities(authorities)
                .build();
        roleRepo.save(member);
        log.debug("Success initial member role");
    }

    private void initialDefaultAuthorities() {
        initialDefaultAuthority(Authority.READ);
        initialDefaultAuthority(Authority.READ_PRIVILEGE);
        initialDefaultAuthority(Authority.WRITE);
        initialDefaultAuthority(Authority.WRITE_PRIVILEGE);
    }

    private void initialDefaultAuthority(String name) {
        Optional<Authority> authorityOptional = authorityRepo.findAuthorityActiveByName(name);
        if (authorityOptional.isPresent()) {
            log.debug(String.format("Default authority [ %s ] is created", name));
            return;
        }
        Authority authority = Authority.builder()
                .name(name)
                .description(name)
                .status(Status.ACTIVE)
                .build();
        authorityRepo.save(authority);
        log.debug(String.format("Success initial authority [ %s ]", name));
    }

    private void initialDefaultAdminUser() {
        Optional<User> adminUser = userRepo.findUserByEmail(defaultAdminUser);
        if (adminUser.isPresent()) {
            log.debug(String.format("Default admin user [ %s ] is created", defaultAdminUser));
            return;
        }
        Role adminRole = roleRepo.getAdmin();
        char[] initialPassword = PasswordUtil.generatePassword(32);
        exportPassword(initialPassword, "initial-admin-password");
        User user = User.builder()
                .email(defaultAdminUser)
                .emailVerified(true)
                .username(defaultAdminUser)
                .preferredUsername(defaultAdminUser)
                .password(passwordEncoder.encode(String.valueOf(initialPassword)))
                .enabled(true)
                .credsExpired(false)
                .accExpired(false)
                .accLocked(false)
                .roles(Set.of(adminRole))
                .build();
        userRepo.save(user);
        log.debug("Success initial default app admin");
    }

}
