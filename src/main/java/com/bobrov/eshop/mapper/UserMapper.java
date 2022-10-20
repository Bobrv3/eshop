package com.bobrov.eshop.mapper;

import com.bobrov.eshop.dto.request.UserRequest;
import com.bobrov.eshop.dto.response.UserResponse;
import com.bobrov.eshop.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", expression = "java(passwordEncoder.encode(userDto.getPassword()))")
    User toUser(UserRequest userDto, PasswordEncoder passwordEncoder);

    UserResponse toResponse(User user);

    List<UserResponse> toListResponse(List<User> users);

    @Mapping(target = "password", expression = "java(passwordEncoder.encode(userRequest.getPassword()))")
    void updateModel(UserRequest userRequest, @MappingTarget User user, PasswordEncoder passwordEncoder);
}
