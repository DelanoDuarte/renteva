package com.app.renteva.document;

import com.app.renteva.document.demand.resource.DocumentOfferDemandResource;
import com.app.renteva.document.demand.resource.NewDocumentOfferDemandResource;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Api(tags = "DocumentOfferAttachmentAPI")
@RequestMapping(value = "/document-offer-attachment", produces = MediaType.APPLICATION_JSON_VALUE)
public interface DocumentOfferAttachmentApi {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<DocumentOfferDemandResource> attach(@RequestBody @Valid NewDocumentOfferDemandResource offerDemandResource);
}
