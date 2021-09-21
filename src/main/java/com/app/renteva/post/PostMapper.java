package com.app.renteva.post;

import com.app.renteva.place.Place;
import com.app.renteva.place.PlaceMapper;
import com.app.renteva.place.PlaceRepository;
import com.app.renteva.post.resource.NewPostResource;
import com.app.renteva.post.resource.PostResource;
import com.app.renteva.user.owner.Owner;
import com.app.renteva.user.owner.OwnerRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Mapper(componentModel = "spring", uses = {PlaceMapper.class})
public abstract class PostMapper {

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    PlaceRepository placeRepository;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(source = "placeId", target = "place", qualifiedByName = "getPlace")
    @Mapping(source = "ownerId", target = "creator", qualifiedByName = "getOwner")
    abstract Post newPost(NewPostResource newPostResource);

    abstract PostResource toResource(Post post);

    @Named("getPlace")
    public Place getPlace(Long placeId) {
        return placeRepository.findById(placeId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Place with given id %s was not found.", placeId)));
    }

    @Named("getOwner")
    public Owner getOwner(Long ownerId) {
        return ownerRepository.findById(ownerId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Owner with given id %s was not found.", ownerId)));
    }
}
