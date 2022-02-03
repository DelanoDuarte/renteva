package com.app.renteva.dataset;

import com.app.renteva.place.Place;
import com.app.renteva.place.PlaceSequence;
import com.app.renteva.place.PlaceType;

import java.math.BigDecimal;

public class PlaceDataset {

    public static Place getExistingPlace() {
        return Place.builder()
                .id(1L)
                .code(new PlaceSequence("P-000001"))
                .buildingYear(1978)
                .bathrooms(2)
                .bedrooms(2)
                .name("New Place")
                .placeType(PlaceType.APARTMENT)
                .parking(true)
                .price(new BigDecimal("1165"))
                .build();
    }
}
