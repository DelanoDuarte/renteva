package com.app.renteva.offer;

import com.app.renteva.offer.resource.NewRentOfferResource;
import com.app.renteva.place.Place;
import com.app.renteva.place.PlaceRepository;
import com.app.renteva.user.User;
import com.app.renteva.user.UserService;
import com.app.renteva.user.renter.Renter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
class RentOfferServiceImpl implements RentOfferService {

    RentOfferRepository rentOfferRepository;
    PlaceRepository placeRepository;

    UserService userService;

    @Override
    public RentOffer create(NewRentOfferResource newRentOfferResource) {

        Place place = placeRepository.findById(newRentOfferResource.getPlaceId())
                .orElseThrow(() -> new IllegalArgumentException("Place not found"));

        User user = userService.getCurrentUser()
                .orElseThrow(() -> new IllegalArgumentException("User not found. Log in first"));

        if (!(user instanceof Renter))
            throw new IllegalArgumentException("You're not logged in as Renter. Log in as a Renter first");

        RentOffer rentOffer = RentOfferMapper.INSTANCE.toRentOffer(newRentOfferResource);
        RentOffer offer = rentOffer.toBuilder()
                .renter((Renter) user)
                .place(place)
                .build();

        return rentOfferRepository.save(offer);
    }
}
