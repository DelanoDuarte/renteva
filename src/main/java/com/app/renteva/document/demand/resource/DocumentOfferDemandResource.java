package com.app.renteva.document.demand.resource;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DocumentOfferDemandResource {

    @NotNull
    Long id;

    String name;
    String description;
    boolean active;
}
