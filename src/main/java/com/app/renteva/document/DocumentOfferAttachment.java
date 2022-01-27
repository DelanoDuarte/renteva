package com.app.renteva.document;

import com.app.renteva.document.demand.DocumentOfferDemand;
import com.app.renteva.offer.RentOffer;
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

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    RentOffer rentOffer;
}
