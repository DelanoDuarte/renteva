package com.app.renteva.user.resource;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SingleAuthenticatedUserResource {

    Long id;
    String fullName;
    String email;
    String token;
}
