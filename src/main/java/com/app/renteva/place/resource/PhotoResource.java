package com.app.renteva.place.resource;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhotoResource {

    Long id;
    String name;
    String path;
}
