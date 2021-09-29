package com.app.renteva.post;

import com.app.renteva.post.resource.NewPostPlaceResource;
import com.app.renteva.post.resource.PostResource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class PostController implements PostApi {

    PostRepository postRepository;
    PostService postService;
    PostMapper postMapper;

    @Override
    public List<PostResource> all() {
        return postRepository.findAll().stream().map(postMapper::toResource).collect(Collectors.toList());
    }

    @SneakyThrows
    @Override
    public ResponseEntity<PostResource> create(
            List<MultipartFile> photos,
            String postPlaceResource) {
        NewPostPlaceResource newPostPlaceResource = getPostPlaceResourceFromJson(postPlaceResource);
        Optional<Post> newPost = Optional.of(postService.create(newPostPlaceResource, photos));
        return newPost
                .map(postMapper::toResource)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @Override
    public ResponseEntity<PostResource> create(NewPostPlaceResource newPostPlaceResource) {
        Optional<Post> newPost = Optional.of(postService.create(newPostPlaceResource));
        return newPost
                .map(postMapper::toResource)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }


    private NewPostPlaceResource getPostPlaceResourceFromJson(String post) throws JsonProcessingException {
        return new ObjectMapper().readValue(post, NewPostPlaceResource.class);
    }
}
