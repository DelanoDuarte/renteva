package com.app.renteva.post;

import com.app.renteva.post.resource.NewPostPlaceResource;

public interface PostService {

    Post create(NewPostPlaceResource newPostPlaceResource);
}
