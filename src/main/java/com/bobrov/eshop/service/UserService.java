package com.bobrov.eshop.service;

import com.bobrov.eshop.dto.request.UserRequest;
import com.bobrov.eshop.dto.response.UserResponse;
import com.bobrov.eshop.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserResponse save(UserRequest userRequest);

    List<User> findAll(Integer offset, Integer limit);

    UserResponse update(UserRequest userRequest);

    void delete(Long id);
}
