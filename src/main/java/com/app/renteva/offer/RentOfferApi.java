package com.app.renteva.offer;

import com.app.renteva.offer.resource.NewRentOfferResource;
import com.app.renteva.offer.resource.RentOfferCreatedResource;
import com.app.renteva.offer.resource.RentOfferListResource;
import com.app.renteva.shared.exceptions.ResourceNotFoundException;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "RentOfferAPI")
@RequestMapping(value = "/rent-offer", produces = MediaType.APPLICATION_JSON_VALUE)
public interface RentOfferApi {

    @GetMapping
    List<RentOfferListResource> getAll();

    @GetMapping("/{id}")
    ResponseEntity<RentOffer> getById(@PathVariable("id") Long id) throws ResourceNotFoundException;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RentOfferCreatedResource> create(@RequestBody @Valid NewRentOfferResource newRentOfferResource);
}
