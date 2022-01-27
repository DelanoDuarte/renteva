package com.app.renteva.document.demand;

import com.app.renteva.document.demand.resource.DocumentOfferDemandResource;
import com.app.renteva.document.demand.resource.NewDocumentOfferDemandResource;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DocumentOfferDemandController implements DocumentOfferDemandApi {

    DocumentOfferDemandMapper offerDemandMapper;

    DocumentOfferDemandRepository offerDemandRepository;

    @Override
    public List<DocumentOfferDemandResource> getAll() {
        return offerDemandRepository.findAll()
                .stream()
                .map(offerDemandMapper::toDocumentOfferDemandResource)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<DocumentOfferDemandResource> create(@Valid NewDocumentOfferDemandResource offerDemandResource) {
        final Optional<DocumentOfferDemand> documentOfferDemand = Optional.of(offerDemandRepository.save(offerDemandMapper.toDocumentOfferDemand(offerDemandResource)));
        return documentOfferDemand
                .map(offerDemandMapper::toDocumentOfferDemandResource)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}
