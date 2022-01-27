package com.app.renteva.offer;

import com.app.renteva.document.DocumentOfferAttachmentService;
import com.app.renteva.offer.resource.NewRentOfferResource;
import com.app.renteva.offer.resource.RentOfferAttachment;
import com.app.renteva.place.Place;
import com.app.renteva.place.PlaceRepository;
import com.app.renteva.user.User;
import com.app.renteva.user.UserService;
import com.app.renteva.user.renter.Renter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
class RentOfferServiceImpl implements RentOfferService {

    RentOfferRepository rentOfferRepository;
    PlaceRepository placeRepository;

    UserService userService;
    DocumentOfferAttachmentService offerAttachmentService;

    @Override
    public RentOffer create(NewRentOfferResource newRentOfferResource) {

        final Place place = placeRepository.findById(newRentOfferResource.getPlaceId())
                .orElseThrow(() -> new IllegalArgumentException("Place not found"));

        final User user = userService.getCurrentUser()
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

    @Override
    public RentOffer attachOfferAttachment(RentOfferAttachment rentOfferAttachment) {

        RentOffer rentOffer = rentOfferRepository.findById(rentOfferAttachment.getRentOfferId())
                .orElseThrow(() -> new IllegalArgumentException("Rent Offer not found"));

        final Place place = rentOffer.getPlace();

        final List<MultipartFile> attachments = rentOfferAttachment.getAttachments();

        offerAttachmentService.buildAttachmentsForDocumentDemand(rentOfferAttachment.getOfferDemandId(), attachments, place.getCode().getCode())
                .forEach(rentOffer::addOfferAttachment);

        return rentOfferRepository.save(rentOffer);
    }
}
