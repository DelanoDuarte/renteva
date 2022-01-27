package com.app.renteva.document.resource;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DocumentAttachmentListResource {

    Long id;
    String name;
    String description;
    String path;
}

