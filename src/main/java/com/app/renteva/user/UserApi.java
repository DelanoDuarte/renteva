package com.app.renteva.user;

import com.app.renteva.user.exceptions.UserNotFoundException;
import com.app.renteva.user.resource.NewUserResource;
import com.app.renteva.user.resource.UserCreatedResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface UserApi {

    @PostMapping("/owner")
    ResponseEntity<UserCreatedResource> createOwner(@RequestBody @Valid NewUserResource newUserResource);

    @PostMapping("/renter")
    ResponseEntity<UserCreatedResource> createRenter(@RequestBody @Valid NewUserResource newUserResource);

    @GetMapping("/{id}")
    ResponseEntity<UserCreatedResource> getById(@PathVariable("id") Long id) throws UserNotFoundException;
}
