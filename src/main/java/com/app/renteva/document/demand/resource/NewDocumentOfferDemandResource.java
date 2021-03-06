package com.app.renteva.document.demand.resource;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewDocumentOfferDemandResource {

    @NotNull
    String name;

    String description;
}
