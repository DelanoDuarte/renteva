package com.app.renteva.address.resource;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressResource {

    @NotNull
    String mainAddress;

    String additionalAddress;

    @NotNull
    String streetNumber;
    @NotNull
    String city;
    @NotNull
    String country;
}
