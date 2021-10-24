package com.app.renteva.post;

import com.app.renteva.place.PlaceMapper;
import com.app.renteva.post.resource.NewPostPlaceResource;
import com.app.renteva.post.resource.PostResource;
import com.app.renteva.user.UserMapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Mapper(componentModel = "spring", uses = {PlaceMapper.class, UserMapper.class})
public abstract class PostMapper {

    abstract Post toPost(NewPostPlaceResource newPostPlaceResource);

    @Mapping(source = "creator", target = "owner")
    abstract PostResource toResource(Post post);
}
