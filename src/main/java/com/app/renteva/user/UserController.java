package com.app.renteva.user;

import com.app.renteva.shared.token.JwtService;
import com.app.renteva.user.exceptions.InvalidPasswordException;
import com.app.renteva.user.exceptions.UserNotFoundException;
import com.app.renteva.user.owner.Owner;
import com.app.renteva.user.owner.OwnerRepository;
import com.app.renteva.user.renter.Renter;
import com.app.renteva.user.renter.RenterRepository;
import com.app.renteva.user.resource.LoginResource;
import com.app.renteva.user.resource.NewUserResource;
import com.app.renteva.user.resource.SingleAuthenticatedUserResource;
import com.app.renteva.user.resource.UserCreatedResource;
import com.app.renteva.user.resource.UserResource;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController implements UserApi {

    UserRepository userRepository;

    OwnerRepository ownerRepository;
    RenterRepository renterRepository;
    UserService userService;
    JwtService jwtService;

    @Override
    public ResponseEntity<UserCreatedResource> createOwner(@Valid NewUserResource newUserResource) {
        Owner ownerRequest = UserMapper.INSTANCE.toOwner(newUserResource);
        ownerRequest.setPassword(userService.encodePassword(ownerRequest.getPassword()));
        Optional<Owner> owner = Optional.of(ownerRepository.save(ownerRequest));
        return owner
                .map(UserMapper.INSTANCE::toUserCreatedResource)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @Override
    public ResponseEntity<UserCreatedResource> createRenter(@Valid NewUserResource newUserResource) {
        Renter renterRequest = UserMapper.INSTANCE.toRenter(newUserResource);
        renterRequest.setPassword(userService.encodePassword(renterRequest.getPassword()));
        Optional<Renter> renter = Optional.of(renterRepository.save(renterRequest));
        return renter
                .map(UserMapper.INSTANCE::toUserCreatedResource)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @Override
    public ResponseEntity<UserCreatedResource> getById(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException(String.format("User not found with given id %s", id));

        return user.map(UserMapper.INSTANCE::toUserCreatedResource)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @Override
    public ResponseEntity<SingleAuthenticatedUserResource> login(@Valid LoginResource loginResource) {
        return userService
                .authenticate(loginResource.getLogin(), loginResource.getPassword())
                .map(u -> ok(fromUser(u, jwtService.generateToken(u))))
                .orElseThrow(() -> new InvalidPasswordException("Can not authenticate - " + loginResource.getLogin()));
    }

    private SingleAuthenticatedUserResource fromUser(User user, String token) {
        return SingleAuthenticatedUserResource
                .builder()
                .email(user.getEmail())
                .fullName(user.getFullName())
                .id(user.getId())
                .token(token)
                .build();
    }

    @Override
    public ResponseEntity<SingleAuthenticatedUserResource> register() {
        return null;
    }

    @Override
    public ResponseEntity<UserResource> getCurrentUser() {
        return userService.getCurrentUser()
                .map(UserMapper.INSTANCE::toUserResource)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException(""));
    }
}
