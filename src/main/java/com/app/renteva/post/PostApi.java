package com.app.renteva.post;

import com.app.renteva.post.resource.NewPostResource;
import com.app.renteva.post.resource.PostResource;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Api(tags = "PostAPI")
@RequestMapping(value = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
public interface PostApi {

    @GetMapping
    List<PostResource> all();

    @PostMapping
    ResponseEntity<PostResource> create(@RequestBody NewPostResource newPostResource);
}
