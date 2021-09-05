package com.app.renteva.place.resource;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class NewPlaceResource {

    @NotBlank
    @NotNull
    String name;

    String description;
    Long longitude;
    Long latitude;

    @NotNull
    Integer bedrooms;

    @NotNull
    Integer bathrooms;
}
