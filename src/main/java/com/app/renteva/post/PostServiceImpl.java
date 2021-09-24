package com.app.renteva.post;

import com.app.renteva.post.resource.NewPostPlaceResource;
import com.app.renteva.user.UserService;
import com.app.renteva.user.owner.Owner;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
class PostServiceImpl implements PostService {

    PostMapper postMapper;
    UserService userService;
    PostRepository postRepository;

    @Override
    public Post create(NewPostPlaceResource newPostPlaceResource) {
        Post newPost = postMapper.toPost(newPostPlaceResource);
        userService.getCurrentUser().ifPresent(user -> newPost.setCreator((Owner) user));
        return postRepository.save(newPost);
    }
}
