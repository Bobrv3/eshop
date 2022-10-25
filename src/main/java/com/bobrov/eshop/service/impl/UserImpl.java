package com.bobrov.eshop.service.impl;

import com.bobrov.eshop.dao.UserRepository;
import com.bobrov.eshop.dto.request.UserRequest;
import com.bobrov.eshop.dto.response.UserResponse;
import com.bobrov.eshop.exception.UserNotFoundException;
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
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<User> findAll(Integer offset, Integer limit) {
        Page<User> users = userRepository.findAll(PageRequest.of(offset, limit));

        return users.getContent();
    }

    @Override
    public UserResponse save(UserRequest userRequest) {
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new RuntimeException("User with such name's already exists");
        } else if (!userRequest.getPassword().equals(userRequest.getRepeatPassword())) {
            throw new RuntimeException("passwords don't match");
        }

        User user = UserMapper.INSTANCE.toUser(userRequest, passwordEncoder);

        return UserMapper.INSTANCE.toResponse(
                userRepository.save(user)
        );
    }

    @Override
    public UserResponse update(UserRequest userRequest) {
        User updatedUser = userRepository.findById(userRequest.getId())
                .orElseThrow(UserNotFoundException::new);
        Optional<User> existedUser = userRepository.findByUsername(userRequest.getUsername());

        if (!existedUser.isPresent() || Objects.equals(updatedUser.getId(), existedUser.get().getId())) {
            checkPasswords(userRequest);
        } else {
            throw new RuntimeException(String.format("user with name '%s' has already exists", userRequest.getUsername()));
        }

        UserMapper.INSTANCE.updateModel(userRequest, updatedUser, passwordEncoder);

        return UserMapper.INSTANCE.toResponse(
                userRepository.save(updatedUser)
        );
    }

    @Override
    public void delete(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException(String.format("No user with id %s exists", id));
        }
    }

    private void checkPasswords(UserRequest userRequest) {
        if (!userRequest.getPassword().equals(userRequest.getRepeatPassword())) {
            throw new RuntimeException("passwords don't match");
        }
    }
}
