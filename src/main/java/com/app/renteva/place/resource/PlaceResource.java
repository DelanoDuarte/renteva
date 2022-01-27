package com.app.renteva.place.resource;

import com.app.renteva.document.demand.resource.DocumentOfferDemandResource;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class PlaceResource {

    Long id;
    String name;
    String description;
    String code;
    Long longitude;
    Long latitude;
    Integer bedrooms;
    Integer bathrooms;
    BigDecimal price;
    Integer floor;
    Integer grossAreaSize;
    Integer usefulAreaSize;
    boolean parking;
    List<PhotoResource> photos;
    List<String> additionalDetails;
    List<DocumentOfferDemandResource> documentOfferDemands;
}
