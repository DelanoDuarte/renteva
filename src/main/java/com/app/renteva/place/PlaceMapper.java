package com.app.renteva.place;

import com.app.renteva.address.AddressMapper;
import com.app.renteva.place.resource.NewPlaceResource;
import com.app.renteva.place.resource.PlaceResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Objects;

@Mapper(componentModel = "spring", uses = AddressMapper.class)
public interface PlaceMapper {

    PlaceMapper INSTANCE = Mappers.getMapper(PlaceMapper.class);

    @Mapping(target = "id", ignore = true)
    Place toPlace(NewPlaceResource placeResource);

    PlaceResource toPlaceResource(Place place);

    default String map(PlaceSequence placeSequence) {
        if (Objects.nonNull(placeSequence))
            return placeSequence.getCode();
        return null;
    }
}
