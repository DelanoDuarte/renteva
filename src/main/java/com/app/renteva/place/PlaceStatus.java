package com.app.renteva.place;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum PlaceStatus {

    AVAILABLE("Available"),
    RENTED("Rented");

    String status;

    @JsonValue
    public String getStatus() {
        return status;
    }

    public static PlaceStatus from(String status) {
        return Arrays.stream(values())
                .filter(ps -> ps.status.equalsIgnoreCase(status))
                .findFirst()
                .orElse(null);
    }
}
