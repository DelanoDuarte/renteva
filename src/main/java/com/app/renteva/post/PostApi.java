package com.app.renteva.post;

import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Api(tags = "PostApi")
@RequestMapping(value = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
public interface PostApi {

    @GetMapping
    List<Post> all();
}
