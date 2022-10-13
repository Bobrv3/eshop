package com.bobrov.eshop.service.impl;

import com.bobrov.eshop.dao.UserRepository;
import com.bobrov.eshop.dto.UserDto;
import com.bobrov.eshop.mapper.UserMapper;
import com.bobrov.eshop.model.User;
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
    public UserDto save(UserDto userDto) {
        User user = repo.save(UserMapper.INSTANCE.toUser(userDto, passwordEncoder));
        return UserMapper.INSTANCE.toDto(user);
    }
}
