package com.app.renteva.place.resource;

import com.app.renteva.address.resource.AddressResource;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


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

    @Min(1)
    @NotNull
    BigDecimal rent;

    @NotNull
    Integer bathrooms;

    @NotNull
    Integer buildingYear;

    @NotNull
    @Valid
    AddressResource address;
}
