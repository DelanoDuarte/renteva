package com.app.renteva.document;

import com.app.renteva.document.demand.DocumentOfferDemand;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "document_offer_attachment")
@Builder(toBuilder = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class DocumentOfferAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @Column
    String name;

    @Column
    String description;

    @NotNull
    @Column
    String path;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn
    DocumentOfferDemand documentOfferDemand;

    public DocumentOfferAttachment(String name, String description, String path, DocumentOfferDemand documentOfferDemand) {
        this.name = name;
        this.description = description;
        this.path = path;
        this.documentOfferDemand = documentOfferDemand;
    }
}
