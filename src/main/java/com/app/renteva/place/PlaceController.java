package com.app.renteva.place;

import com.app.renteva.place.resource.AddDemandPlaceResource;
import com.app.renteva.place.resource.NewPlaceResource;
import com.app.renteva.place.resource.PlaceResource;
import com.app.renteva.shared.exceptions.ResourceNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PlaceController implements PlaceApi {

    PlaceRepository placeRepository;

    PlaceService placeService;

    @Override
    public List<Place> all() {
        return placeRepository.findAll();
    }

    @Override
    public ResponseEntity<Place> getById(Long id) throws ResourceNotFoundException {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Place not found with given id %s", id)));
        return ResponseEntity.ok(place);
    }

    @Override
    public ResponseEntity<Place> create(NewPlaceResource placeResource) {
        Place placeRequest = PlaceMapper.INSTANCE.toPlace(placeResource);
        Optional<Place> place = Optional.of(placeRepository.save(placeRequest));
        return place.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @Override
    public ResponseEntity<PlaceResource> addDocumentOfferDemands(Long id, @Valid AddDemandPlaceResource demandPlaceResource) {
        final Optional<Place> place = Optional.of(placeService.addDocumentOfferDemands(id, demandPlaceResource));
        return place
                .map(PlaceMapper.INSTANCE::toPlaceResource)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}
