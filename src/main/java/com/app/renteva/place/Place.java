package com.app.renteva.place;

import com.app.renteva.address.Address;
import com.app.renteva.document.demand.DocumentOfferDemand;
import com.app.renteva.place.photo.Photo;
import com.app.renteva.user.owner.Owner;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "place")
@Builder
@EqualsAndHashCode
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Place {

    // properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Builder.Default
    @OneToOne(cascade = CascadeType.PERSIST)
    PlaceSequence code = new PlaceSequence();

    @NotNull
    @Column
    String name;
    String description;
    Long longitude;
    Long latitude;

    @NotNull
    @Column
    Integer bedrooms;

    @NotNull
    @Column
    Integer bathrooms;

    @NotNull
    @Column
    Integer floor;

    @NotNull
    @Column
    Integer grossAreaSize;

    @NotNull
    @Column
    Integer usefulAreaSize;

    @NotNull
    @Column
    boolean parking;

    @NotNull
    @Column
    Integer buildingYear;

    @NotNull
    BigDecimal price;

    // relationships
    @ManyToOne
    @JoinColumn
    Owner owner;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn
    Address address;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    Set<Photo> photos;

    @Enumerated(EnumType.ORDINAL)
    PlaceType placeType;

    @Builder.Default
    @Enumerated(EnumType.ORDINAL)
    PlaceStatus status = PlaceStatus.AVAILABLE;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    Set<DocumentOfferDemand> documentOfferDemands;

    public void addPhoto(Photo photo) {
        if (Objects.isNull(getPhotos())) {
            this.photos = new HashSet<>();
        }
        this.photos.add(photo);
    }

    public void addOfferDemand(DocumentOfferDemand demand) {
        if (Objects.isNull(getDocumentOfferDemands())) {
            this.documentOfferDemands = new HashSet<>();
        }
        this.documentOfferDemands.add(demand);
    }
}
