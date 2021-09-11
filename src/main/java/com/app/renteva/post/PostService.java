package com.app.renteva.post;

import com.app.renteva.post.resource.NewPostResource;

public interface PostService {

    Post create(NewPostResource newPostResource);
}
