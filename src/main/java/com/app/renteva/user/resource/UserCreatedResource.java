package com.app.renteva.user.resource;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreatedResource {

    Long id;
    String fullName;
    String firstName;
    String lastName;

    String email;
    Boolean active;
}
