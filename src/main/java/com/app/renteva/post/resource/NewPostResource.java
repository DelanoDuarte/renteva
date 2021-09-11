package com.app.renteva.post.resource;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class NewPostResource {

    @NotNull
    String title;

    String comments;

    @NotNull
    Long placeId;

    @NotNull
    Long ownerId;
}
