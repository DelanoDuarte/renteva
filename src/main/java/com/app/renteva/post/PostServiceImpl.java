package com.app.renteva.post;

import com.app.renteva.place.PlaceRepository;
import com.app.renteva.post.resource.NewPostResource;
import com.app.renteva.user.owner.OwnerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
class PostServiceImpl implements PostService {

    PostRepository postRepository;

    PlaceRepository placeRepository;
    OwnerRepository ownerRepository;

    @Override
    public Post create(NewPostResource newPostResource) {
        return null;
    }
}
