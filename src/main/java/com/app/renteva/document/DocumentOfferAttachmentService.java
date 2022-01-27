package com.app.renteva.document;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentOfferAttachmentService {

    List<DocumentOfferAttachment> buildAttachmentsForDocumentDemand(Long offerDemandId, List<MultipartFile> attachments, String code);
}
