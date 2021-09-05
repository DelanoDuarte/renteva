package com.app.renteva.user;

import com.app.renteva.user.exceptions.UserNotFoundException;
import com.app.renteva.user.owner.Owner;
import com.app.renteva.user.owner.OwnerRepository;
import com.app.renteva.user.renter.RenterRepository;
import com.app.renteva.user.resource.NewUserResource;
import com.app.renteva.user.resource.UserCreatedResource;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController implements UserApi {

    UserRepository userRepository;
    OwnerRepository ownerRepository;
    RenterRepository renterRepository;

    @Override
    public ResponseEntity<UserCreatedResource> createOwner(@Valid NewUserResource newUserResource) {
        Owner ownerRequest = UserMapper.INSTANCE.toOwner(newUserResource);
        Optional<Owner> owner = Optional.of(ownerRepository.save(ownerRequest));
        return owner
                .map(UserMapper.INSTANCE::toUserCreatedResource)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @Override
    public ResponseEntity<UserCreatedResource> createRenter(@Valid NewUserResource newUserResource) {
        return null;
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
}
