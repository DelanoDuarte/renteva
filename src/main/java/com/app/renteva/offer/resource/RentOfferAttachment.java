package com.app.renteva.offer.resource;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RentOfferAttachment {

    @NotNull
    Long rentOfferId;

    @NotNull
    Long offerDemandId;

    List<MultipartFile> attachments;
}
