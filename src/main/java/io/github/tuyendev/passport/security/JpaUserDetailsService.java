package io.github.tuyendev.passport.security;

import io.github.tuyendev.passport.entity.User;
import io.github.tuyendev.passport.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo;

    public JpaUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findUserByEmail(email);
        if (user.isPresent()) {
            return new SecuredUserDetails(user.get());
        }
        throw new UsernameNotFoundException("app.user.error.notFound");
    }
}
