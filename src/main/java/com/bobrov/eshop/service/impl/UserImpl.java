package com.bobrov.eshop.service.impl;

import com.bobrov.eshop.dao.UserRepository;
import com.bobrov.eshop.model.User;
import com.bobrov.eshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserImpl implements UserService {
    private final UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
    }

    @Override
    public User save(User user) {
        return repo.save(user);
    }
}
