package com.app.renteva.post;

import com.app.renteva.place.PlaceMapper;
import com.app.renteva.post.resource.NewPostResource;
import com.app.renteva.post.resource.PostResource;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class PostController implements PostApi {

    PostRepository postRepository;

    PostMapper postMapper;

    @Override
    public List<PostResource> all() {
        return postRepository.findAll().stream().map(postMapper::toResource).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<PostResource> create(NewPostResource newPostResource) {
        Post post = postMapper.newPost(newPostResource);
        Optional<Post> newPost = Optional.of(postRepository.save(post));
        return newPost
                .map(postMapper::toResource)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}
