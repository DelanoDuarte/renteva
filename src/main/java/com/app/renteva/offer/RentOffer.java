package com.app.renteva.offer;

import com.app.renteva.document.DocumentOfferAttachment;
import com.app.renteva.place.Place;
import com.app.renteva.user.renter.Renter;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "rent_offer")
@Builder(toBuilder = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class RentOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @Min(0)
    BigDecimal offerAmount;

    String comments;

    @ManyToOne
    @JoinColumn
    Place place;

    @ManyToOne
    @JoinColumn
    Renter renter;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    Set<DocumentOfferAttachment> offerAttachments;

    public void addOfferAttachment(DocumentOfferAttachment attachment) {
        if (Objects.isNull(getOfferAttachments())) {
            this.offerAttachments = new HashSet<>();
        }
        this.offerAttachments.add(attachment);
    }
}
