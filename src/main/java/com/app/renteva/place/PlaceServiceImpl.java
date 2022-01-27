package com.app.renteva.place;

import com.app.renteva.document.demand.DocumentOfferDemandRepository;
import com.app.renteva.document.demand.resource.DocumentOfferDemandResource;
import com.app.renteva.place.resource.AddDemandPlaceResource;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    PlaceRepository placeRepository;
    DocumentOfferDemandRepository offerDemandRepository;

    @Override
    public Place addDocumentOfferDemands(Long placeId, AddDemandPlaceResource demandPlaceResource) {

        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new IllegalArgumentException("Place not found"));

        final List<DocumentOfferDemandResource> offerDemandResources = demandPlaceResource.getOfferDemandResources();
        if (offerDemandResources != null && !offerDemandResources.isEmpty()) {
            offerDemandResources.stream().map(DocumentOfferDemandResource::getId)
                    .map(offerDemandRepository::getById)
                    .forEach(place::addOfferDemand);
        }

        return placeRepository.save(place);
    }
}
