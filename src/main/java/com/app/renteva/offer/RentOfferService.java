package com.app.renteva.offer;

import com.app.renteva.offer.resource.NewRentOfferResource;
import com.app.renteva.offer.resource.RentOfferAttachment;

public interface RentOfferService {

    RentOffer create(NewRentOfferResource newRentOfferResource);

    RentOffer attachOfferAttachment(RentOfferAttachment rentOfferAttachment);
}
