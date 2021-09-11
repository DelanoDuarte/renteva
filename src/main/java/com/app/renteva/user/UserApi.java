package com.app.renteva.user;

import com.app.renteva.user.resource.LoginResource;
import com.app.renteva.user.resource.SingleAuthenticatedUserResource;
import com.app.renteva.user.exceptions.UserNotFoundException;
import com.app.renteva.user.resource.NewUserResource;
import com.app.renteva.user.resource.UserCreatedResource;
import com.app.renteva.user.resource.UserResource;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Api(tags = "UserAPI")
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public interface UserApi {

    @PostMapping(value = "/owner", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserCreatedResource> createOwner(@RequestBody @Valid NewUserResource newUserResource);

    @PostMapping(value = "/renter", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserCreatedResource> createRenter(@RequestBody @Valid NewUserResource newUserResource);

    @GetMapping("/{id}")
    ResponseEntity<UserCreatedResource> getById(@PathVariable("id") Long id) throws UserNotFoundException;

    @PostMapping(value = "/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SingleAuthenticatedUserResource> login(@RequestBody @Valid LoginResource loginResource);

    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SingleAuthenticatedUserResource> register();

    @GetMapping("/me")
    ResponseEntity<UserResource> getCurrentUser();
}
