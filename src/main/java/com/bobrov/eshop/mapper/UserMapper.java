package com.bobrov.eshop.mapper;

import com.bobrov.eshop.dto.UserDto;
import com.bobrov.eshop.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", expression = "java(passwordEncoder.encode(userDto.getPassword()))")
    @Mapping(target = "locked", constant = "false")
    @Mapping(target = "enabled", constant = "true")
    @Mapping(target = "role", constant = "ROLE_USER")
    User toUser(UserDto userDto, PasswordEncoder passwordEncoder);

    @Mapping(target = "password", ignore = true)
    UserDto toDto(User user);
}
