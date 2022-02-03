package com.app.renteva.place.resource;

import com.app.renteva.document.demand.resource.DocumentOfferDemandResource;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddDemandPlaceResource {

    List<DocumentOfferDemandResource> offerDemandResources;
}
