package com.app.renteva.place;

import com.app.renteva.place.resource.NewPlaceResource;
import com.app.renteva.shared.exceptions.ResourceNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/places", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PlaceController implements PlaceApi {

    PlaceRepository placeRepository;

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
}
