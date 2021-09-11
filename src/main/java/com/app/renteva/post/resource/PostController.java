package com.app.renteva.post.resource;

import com.app.renteva.post.Post;
import com.app.renteva.post.PostApi;
import com.app.renteva.post.PostRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostController implements PostApi {

    PostRepository postRepository;

    @Override
    public List<Post> all() {
        return postRepository.findAll();
    }
}
