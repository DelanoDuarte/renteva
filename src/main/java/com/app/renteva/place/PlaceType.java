package com.app.renteva.place;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum PlaceType {

    HOUSE("House"),
    APARTMENT("Apartment"),
    STUDIO("Studio"),
    OTHER("Other"),
    DUPLEX("Duplex");

    String type;

    @JsonValue
    public String getType() {
        return type;
    }

    public static PlaceType from(String type) {
        return Arrays.stream(values())
                .filter(placeType -> placeType.type.equalsIgnoreCase(type))
                .findFirst()
                .orElse(null);
    }
}
