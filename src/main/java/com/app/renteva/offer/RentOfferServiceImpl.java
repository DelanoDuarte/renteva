package com.app.renteva.offer;

import com.app.renteva.offer.resource.NewRentOfferResource;
import com.app.renteva.place.Place;
import com.app.renteva.place.PlaceRepository;
import com.app.renteva.user.renter.Renter;
import com.app.renteva.user.renter.RenterRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RentOfferServiceImpl implements RentOfferService {

    RentOfferRepository rentOfferRepository;

    PlaceRepository placeRepository;
    RenterRepository renterRepository;

    @Override
    public RentOffer create(NewRentOfferResource newRentOfferResource) {

        Place place = placeRepository.findById(newRentOfferResource.getPlaceId())
                .orElseThrow(() -> new IllegalArgumentException("Place not found"));

        Renter renter = renterRepository.findById(newRentOfferResource.getRenterId())
                .orElseThrow(() -> new IllegalArgumentException("Renter not found"));

        RentOffer rentOffer = RentOfferMapper.INSTANCE.toRentOffer(newRentOfferResource);
        RentOffer offer = rentOffer.toBuilder()
                .renter(renter)
                .place(place)
                .build();

        return rentOfferRepository.save(offer);
    }
}
