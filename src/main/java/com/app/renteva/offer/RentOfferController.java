package com.app.renteva.offer;

import com.app.renteva.offer.resource.NewRentOfferResource;
import com.app.renteva.offer.resource.RentOfferAttachment;
import com.app.renteva.offer.resource.RentOfferCreatedResource;
import com.app.renteva.offer.resource.RentOfferListResource;
import com.app.renteva.shared.exceptions.ResourceNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RentOfferController implements RentOfferApi {

    RentOfferService rentOfferService;
    RentOfferRepository rentOfferRepository;

    @Override
    public List<RentOfferListResource> getAll() {
        return rentOfferRepository.findAll()
                .stream()
                .map(RentOfferMapper.INSTANCE::toRentOfferListResource)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<RentOffer> getById(Long id) throws ResourceNotFoundException {
        RentOffer rentOffer = rentOfferRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("RentOffer not found with given id %s", id)));
        return ResponseEntity.ok(rentOffer);
    }

    @Override
    public ResponseEntity<RentOfferCreatedResource> create(NewRentOfferResource newRentOfferResource) {
        Optional<RentOffer> rentOffer = Optional.of(rentOfferService.create(newRentOfferResource));
        Optional<RentOfferCreatedResource> rentOfferCreatedResource = rentOffer.map(RentOfferMapper.INSTANCE::toRentOfferCreatedResource);
        return rentOfferCreatedResource.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @Override
    public ResponseEntity<List<RentOfferListResource>> getByPlace(Long placeId) {
        List<RentOfferListResource> offersByPlace = rentOfferRepository.findByPlaceId(placeId)
                .stream()
                .map(RentOfferMapper.INSTANCE::toRentOfferListResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(offersByPlace);
    }

    @Override
    public ResponseEntity<RentOfferListResource> attachOfferDocument(Long rentOfferId, Long demandId, List<MultipartFile> attachments) {
        RentOfferAttachment rentOfferAttachment = RentOfferAttachment.builder()
                .rentOfferId(rentOfferId)
                .offerDemandId(demandId)
                .attachments(attachments)
                .build();

        final Optional<RentOffer> rentOffer = Optional.ofNullable(rentOfferService.attachOfferAttachment(rentOfferAttachment));
        return rentOffer.map(RentOfferMapper.INSTANCE::toRentOfferListResource)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}
