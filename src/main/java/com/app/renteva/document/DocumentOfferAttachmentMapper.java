package com.app.renteva.document;

import com.app.renteva.document.resource.DocumentAttachmentListResource;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DocumentOfferAttachmentMapper {

    DocumentOfferAttachmentMapper INSTANCE = Mappers.getMapper(DocumentOfferAttachmentMapper.class);

    DocumentAttachmentListResource toListResource(DocumentOfferAttachment attachment);
}
