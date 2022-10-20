package com.bobrov.eshop.service.impl;

import com.bobrov.eshop.aop.BeforeSaveUser;
import com.bobrov.eshop.aop.BeforeUpdateUser;
import com.bobrov.eshop.dao.UserRepository;
import com.bobrov.eshop.dto.request.UserRequest;
import com.bobrov.eshop.dto.response.UserResponse;
import com.bobrov.eshop.mapper.UserMapper;
import com.bobrov.eshop.model.User;
import com.bobrov.eshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<User> findAll(Integer offset, Integer limit) {


        Page<User> users = repo.findAll(PageRequest.of(offset, limit));


        return users.getContent();
    }

    /**
     * @see BeforeSaveUser
     */
    @Override
    public UserResponse save(UserRequest userRequest) {
        return UserMapper.INSTANCE.toResponse(
                repo.save(UserMapper.INSTANCE.toSavingUser(userRequest, passwordEncoder))
        );
    }

    /**
     * @see BeforeUpdateUser
     */
    @Override
    public UserResponse update(UserRequest userRequest) {
        return UserMapper.INSTANCE.toResponse(
                repo.save(UserMapper.INSTANCE.toSavingUser(userRequest, passwordEncoder))
        );
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
