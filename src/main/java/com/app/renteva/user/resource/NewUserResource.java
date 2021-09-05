package com.app.renteva.user.resource;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
public class NewUserResource {

    @NotNull
    @NotBlank
    String firstName;

    @NotNull
    @NotBlank
    String lastName;

    @NotNull
    @NotBlank
    String password;

    @NotNull
    @NotBlank
    String email;
}
