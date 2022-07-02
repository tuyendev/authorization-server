package io.github.tuyendev.passport.configurer;

import io.github.tuyendev.passport.entity.User;
import io.github.tuyendev.passport.repository.AuthorityRepository;
import io.github.tuyendev.passport.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo;

    private final AuthorityRepository userAuthorityRepo;

    public JpaUserDetailsService(UserRepository userRepo, AuthorityRepository userAuthorityRepo) {
        this.userRepo = userRepo;
        this.userAuthorityRepo = userAuthorityRepo;
    }

    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    // TODO Handle role authorities base
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findUserByUsernameOrEmail(username, username);
        if (user.isPresent()) {

        }
        throw new UsernameNotFoundException("app.user.error.notFound");
    }
}
