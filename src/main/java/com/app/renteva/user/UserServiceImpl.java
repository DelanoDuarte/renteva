package com.app.renteva.user;

import com.app.renteva.shared.token.JwtService;
import com.app.renteva.user.exceptions.UserAlreadyExistsException;
import com.app.renteva.user.owner.Owner;
import com.app.renteva.user.owner.OwnerRepository;
import com.app.renteva.user.renter.Renter;
import com.app.renteva.user.renter.RenterRepository;
import com.app.renteva.user.resource.SingleAuthenticatedUserResource;
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
    OwnerRepository ownerRepository;
    RenterRepository renterRepository;

    PasswordEncoder passwordEncoder;

    JwtService jwtService;

    @Override
    public Optional<User> create(User user) {

        String email = user.getEmail();
        userRepository.findByEmail(email)
                .ifPresent(u -> {
                    throw new UserAlreadyExistsException(String.format("The given user email %s already exists", email));
                });

        user.setPassword(this.encodePassword(user.getPassword()));
        if (user instanceof Owner) {
            return Optional.of(ownerRepository.save((Owner) user));
        }
        return Optional.of(renterRepository.save((Renter) user));
    }

    @Override
    public Optional<User> getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) return Optional.empty();

        if (authentication.getPrincipal().equals("anonymousUser")) return Optional.empty();

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
    public SingleAuthenticatedUserResource getAuthenticatedUserFromUser(User user) {
        return SingleAuthenticatedUserResource
                .builder()
                .email(user.getEmail())
                .fullName(user.getFullName())
                .id(user.getId())
                .token(jwtService.generateToken(user))
                .build();
    }

    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
