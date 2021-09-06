package com.app.renteva.place.resource;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class PlaceResource {

    Long id;
    String name;
    String description;
    Long longitude;
    Long latitude;
    Integer bedrooms;
    Integer bathrooms;
}
