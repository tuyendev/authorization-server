package io.github.tuyendev.passport.service.user;

import io.github.tuyendev.passport.dto.user.UserDto;
import io.github.tuyendev.passport.entity.User;
import io.github.tuyendev.passport.repository.UserRepository;
import io.github.tuyendev.passport.utils.TextUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto create(UserDto userDto) {
        final String username = TextUtil.generateUuid();
        final String fullname = userDto.getFamilyName() + " " + userDto.getGivenName();
        User user = User.builder()
                .username(username)
                .preferredUsername(username)
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .birthdate(userDto.getBirthdate())
                .familyName(userDto.getFamilyName())
                .givenName(userDto.getGivenName())
                .name(fullname)
                .unsignedName(TextUtil.unsigned(fullname))
                .build();
        userRepo.save(user);
        return null;
    }
}
