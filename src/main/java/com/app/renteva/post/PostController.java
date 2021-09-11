package com.app.renteva.post;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class PostController implements PostApi {

    PostRepository postRepository;

    @Override
    public List<Post> all() {
        return postRepository.findAll();
    }
}
