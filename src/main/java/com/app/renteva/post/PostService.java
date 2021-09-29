package com.app.renteva.post;

import com.app.renteva.post.resource.NewPostPlaceResource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {

    Post create(NewPostPlaceResource newPostPlaceResource);

    Post create(NewPostPlaceResource newPostPlaceResource, List<MultipartFile> photos);
}
