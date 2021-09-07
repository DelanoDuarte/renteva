package com.app.renteva.offer.resource;

import com.app.renteva.place.resource.PlaceResource;
import com.app.renteva.user.resource.UserResource;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RentOfferListResource {

    Long id;
    BigDecimal offerAmount;
    String comments;
    PlaceResource place;
    UserResource renter;
}
