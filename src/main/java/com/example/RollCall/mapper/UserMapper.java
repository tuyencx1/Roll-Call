package com.example.RollCall.mapper;

import com.example.RollCall.dto.repon.UserRespon;
import com.example.RollCall.dto.request.UserRequest;
import com.example.RollCall.entity.Users;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roles", ignore = true)
    Users toUser(UserRequest request);

    UserRespon toUserRepon(Users user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget Users user, UserRequest userRequest);
}
