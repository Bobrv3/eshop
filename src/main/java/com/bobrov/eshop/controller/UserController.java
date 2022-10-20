package com.bobrov.eshop.controller;

import com.bobrov.eshop.dto.request.UserRequest;
import com.bobrov.eshop.dto.response.UserResponse;
import com.bobrov.eshop.mapper.UserMapper;
import com.bobrov.eshop.model.User;
import com.bobrov.eshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{username}")
    public UserResponse get(@PathVariable String username) {
        return UserMapper.INSTANCE.toResponse(
                (User) userService.loadUserByUsername(username)
        );
    }

    @GetMapping
    public List<UserResponse> getAll(
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer limit) {
        return UserMapper.INSTANCE.toListResponse(
                userService.findAll(offset, limit)
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse save(@RequestBody UserRequest userRequest) {
        return userService.save(userRequest);
    }

    @PutMapping
    public UserResponse update(@RequestBody UserRequest userRequest) {
        return userService.update(userRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

}
