package com.app.renteva.document.demand;

import com.app.renteva.document.demand.resource.DocumentOfferDemandResource;
import com.app.renteva.document.demand.resource.NewDocumentOfferDemandResource;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "DocumentOfferDemandAPI")
@RequestMapping(value = "/document-offer-demand", produces = MediaType.APPLICATION_JSON_VALUE)
public interface DocumentOfferDemandApi {

    @GetMapping
    List<DocumentOfferDemandResource> getAll();

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<DocumentOfferDemandResource> create(@RequestBody @Valid NewDocumentOfferDemandResource offerDemandResource);
}
