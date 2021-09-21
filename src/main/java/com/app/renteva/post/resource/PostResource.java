package com.app.renteva.post.resource;

import com.app.renteva.place.resource.PlaceResource;
import com.app.renteva.user.resource.UserResource;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostResource {

    Long id;
    String title;
    String comments;
    PlaceResource place;
    UserResource owner;
}
