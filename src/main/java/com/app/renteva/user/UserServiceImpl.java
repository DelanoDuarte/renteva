package com.app.renteva.user;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return Optional.empty();
        }
        if (authentication.getPrincipal().equals("anonymousUser")) {
            return Optional.empty();
        }
        final var userDetails = (UserDetails) authentication.getPrincipal();
        return userRepository.findByEmail(userDetails.getUsername());
    }

    @Override
    public Optional<String> getCurrentToken() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Optional.ofNullable((String) authentication.getCredentials());
    }

    @Override
    public Optional<User> authenticate(String email, String password) {
        return userRepository
                .findByEmail(email)
                .flatMap(
                        u -> {
                            if (passwordEncoder.matches(password, u.getPassword())) {
                                return Optional.of(u);
                            } else {
                                return Optional.empty();
                            }
                        });
    }

    @Override
    public Optional<User> register() {
        return Optional.empty();
    }

    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
