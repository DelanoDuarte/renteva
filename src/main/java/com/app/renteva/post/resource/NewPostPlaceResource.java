package com.app.renteva.post.resource;

import com.app.renteva.place.resource.NewPlaceResource;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class NewPostPlaceResource {

    @NotNull
    String title;

    String comments;

    @NotNull
    @Valid
    NewPlaceResource place;
}
