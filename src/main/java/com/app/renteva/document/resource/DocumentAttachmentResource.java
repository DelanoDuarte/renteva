package com.app.renteva.document.resource;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DocumentAttachmentResource {

    @NotNull
    String name;

    String description;
}

