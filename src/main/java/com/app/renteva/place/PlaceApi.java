package com.app.renteva.place;

import com.app.renteva.place.resource.NewPlaceResource;
import com.app.renteva.shared.exceptions.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/places", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public interface PlaceApi {

    @GetMapping
    List<Place> all();

    @GetMapping("/{id}")
    ResponseEntity<Place> getById(@PathVariable("id") Long id) throws ResourceNotFoundException;

    @PostMapping
    ResponseEntity<Place> create(@RequestBody @Valid NewPlaceResource placeResource);
}
