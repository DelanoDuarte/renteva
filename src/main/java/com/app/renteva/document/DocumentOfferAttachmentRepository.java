package com.app.renteva.document;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentOfferAttachmentRepository extends JpaRepository<DocumentOfferAttachment, Long> {
}
