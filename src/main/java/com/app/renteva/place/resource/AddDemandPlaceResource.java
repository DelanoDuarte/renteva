package com.app.renteva.place.resource;

import com.app.renteva.document.demand.resource.DocumentOfferDemandResource;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddDemandPlaceResource {

    List<DocumentOfferDemandResource> offerDemandResources;
}
