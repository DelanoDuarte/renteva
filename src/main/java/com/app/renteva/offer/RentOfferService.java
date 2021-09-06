package com.app.renteva.offer;

import com.app.renteva.offer.resource.NewRentOfferResource;

public interface RentOfferService {

    RentOffer create(NewRentOfferResource newRentOfferResource);
}
