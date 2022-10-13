package com.bobrov.eshop.service;

import com.bobrov.eshop.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto save(UserDto userDto);
}
