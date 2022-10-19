package com.bobrov.eshop.controller;

import com.bobrov.eshop.dto.request.UserRequest;
import com.bobrov.eshop.dto.response.UserResponse;
import com.bobrov.eshop.mapper.UserMapper;
import com.bobrov.eshop.model.User;
import com.bobrov.eshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{username}")
    public UserResponse get(@PathVariable String username) {
        return UserMapper.INSTANCE.toResponse(
                (User) userService.loadUserByUsername(username)
        );

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse save(@RequestBody UserRequest userRequest) {
        return userService.save(userRequest);
    }

//    @GetMapping("/profile")
//    public String getUser(Model model, Principal principal) {
//        User user = (User) userService.loadUserByUsername(principal.getName());
//        model.addAttribute("user", UserMapper.INSTANCE.toDto(user));
//
//        return "profile";
//    }
}
