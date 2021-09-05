package com.app.renteva.user;

import com.app.renteva.user.owner.Owner;
import com.app.renteva.user.renter.Renter;
import com.app.renteva.user.resource.NewUserResource;
import com.app.renteva.user.resource.UserCreatedResource;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    Owner toOwner(NewUserResource newUserResource);

    Renter toRenter(NewUserResource newUserResource);

    UserCreatedResource toUserCreatedResource(Owner owner);

    UserCreatedResource toUserCreatedResource(User user);
}
