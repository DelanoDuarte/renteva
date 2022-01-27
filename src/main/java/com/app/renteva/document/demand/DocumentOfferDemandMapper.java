package com.app.renteva.document.demand;

import com.app.renteva.document.demand.resource.DocumentOfferDemandResource;
import com.app.renteva.document.demand.resource.NewDocumentOfferDemandResource;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.mapstruct.Mapper;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Mapper(componentModel = "spring")
public abstract class DocumentOfferDemandMapper {

    abstract DocumentOfferDemand toDocumentOfferDemand(DocumentOfferDemandResource documentOfferDemand);

    abstract DocumentOfferDemand toDocumentOfferDemand(NewDocumentOfferDemandResource offerDemandResource);

    abstract DocumentOfferDemandResource toDocumentOfferDemandResource(DocumentOfferDemand documentOfferDemand);
}
