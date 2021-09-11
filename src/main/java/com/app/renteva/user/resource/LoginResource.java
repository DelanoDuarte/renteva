package com.app.renteva.user.resource;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginResource {

    @Email
    @NotNull
    @NotBlank
    String login;

    @NotNull
    @NotBlank
    String password;
}
