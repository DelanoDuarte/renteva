package com.app.renteva.place;

import com.app.renteva.place.resource.AddDemandPlaceResource;

public interface PlaceService {

    Place addDocumentOfferDemands(Long placeId, AddDemandPlaceResource demandPlaceResource);
}
