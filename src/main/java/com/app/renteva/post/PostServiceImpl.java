package com.app.renteva.post;

import com.app.renteva.place.Place;
import com.app.renteva.place.photo.Photo;
import com.app.renteva.place.photo.PhotoUploadService;
import com.app.renteva.post.resource.NewPostPlaceResource;
import com.app.renteva.user.UserService;
import com.app.renteva.user.owner.Owner;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
class PostServiceImpl implements PostService {

    PostMapper postMapper;
    UserService userService;
    PostRepository postRepository;

    PhotoUploadService photoUploadService;

    @Override
    public Post create(NewPostPlaceResource newPostPlaceResource) {
        Post newPost = postMapper.toPost(newPostPlaceResource);
        userService.getCurrentUser().ifPresent(user -> newPost.setCreator((Owner) user));
        return postRepository.save(newPost);
    }

    @Override
    public Post create(NewPostPlaceResource newPostPlaceResource, List<MultipartFile> photos) {
        Post newPost = postMapper.toPost(newPostPlaceResource);
        Place place = newPost.getPlace();

        photos.stream()
                .map(photo -> new Photo(photo.getOriginalFilename(), photoUploadService.saveWithPlaceReference(photo, place.getName())))
                .forEach(place::addPhoto);

        userService.getCurrentUser().ifPresent(user -> newPost.setCreator((Owner) user));
        return postRepository.save(newPost);
    }
}
