package com.app.renteva.offer.resource;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewRentOfferResource {

    @NotNull
    @Min(0)
    BigDecimal offerAmount;

    String comments;

    @NotNull
    Long placeId;

    @NotNull
    Long renterId;
}
