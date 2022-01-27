package com.app.renteva.offer;

import com.app.renteva.document.DocumentOfferAttachmentMapper;
import com.app.renteva.offer.resource.NewRentOfferResource;
import com.app.renteva.offer.resource.RentOfferCreatedResource;
import com.app.renteva.offer.resource.RentOfferListResource;
import com.app.renteva.place.PlaceMapper;
import com.app.renteva.user.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {PlaceMapper.class, UserMapper.class, DocumentOfferAttachmentMapper.class})
public interface RentOfferMapper {

    RentOfferMapper INSTANCE = Mappers.getMapper(RentOfferMapper.class);

    RentOffer toRentOffer(NewRentOfferResource newRentOfferResource);

    RentOfferListResource toRentOfferListResource(RentOffer rentOffer);

    RentOfferCreatedResource toRentOfferCreatedResource(RentOffer rentOffer);
}
