package com.bobrov.eshop.service.impl;

import com.bobrov.eshop.dao.UserRepository;
import com.bobrov.eshop.dto.request.UserRequest;
import com.bobrov.eshop.dto.response.UserResponse;
import com.bobrov.eshop.mapper.UserMapper;
import com.bobrov.eshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserImpl implements UserService {
    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
    }

    @Override
    public UserResponse save(UserRequest userRequest) {
        return UserMapper.INSTANCE.toResponse(
                repo.save(UserMapper.INSTANCE.toSavingUser(userRequest, passwordEncoder))
        );
    }
}
