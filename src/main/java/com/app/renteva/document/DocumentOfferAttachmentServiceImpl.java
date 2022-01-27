package com.app.renteva.document;

import com.app.renteva.document.demand.DocumentOfferDemand;
import com.app.renteva.document.demand.DocumentOfferDemandRepository;
import com.app.renteva.document.upload.DocumentUploadService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DocumentOfferAttachmentServiceImpl implements DocumentOfferAttachmentService {

    DocumentOfferDemandRepository offerDemandRepository;

    DocumentUploadService documentUploadService;

    @Override
    public List<DocumentOfferAttachment> buildAttachmentsForDocumentDemand(Long offerDemandId, List<MultipartFile> attachments, String placeCode) {

        final DocumentOfferDemand offerDemand = offerDemandRepository.findById(offerDemandId)
                .orElseThrow(() -> new IllegalArgumentException("Offer Demand not found"));

        return attachments
                .stream()
                .map(file -> new DocumentOfferAttachment(
                        file.getOriginalFilename(),
                        "",
                        documentUploadService.saveAttachment(file, placeCode),
                        offerDemand))
                .collect(Collectors.toList());
    }
}
