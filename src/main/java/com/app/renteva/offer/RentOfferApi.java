package com.app.renteva.offer;

import com.app.renteva.offer.resource.NewRentOfferResource;
import com.app.renteva.offer.resource.RentOfferCreatedResource;
import com.app.renteva.offer.resource.RentOfferListResource;
import com.app.renteva.shared.exceptions.ResourceNotFoundException;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/place/{id}")
    ResponseEntity<List<RentOfferListResource>> getByPlace(@PathVariable("id") Long placeId);

    @PostMapping("/{id}/attach/offer-document/{demandId}")
    ResponseEntity<RentOfferListResource> attachOfferDocument(@PathVariable("id") Long rentOfferId,
                                                              @PathVariable("demandId") Long demandId,
                                                              @RequestParam("attachments") List<MultipartFile> attachments);
}
