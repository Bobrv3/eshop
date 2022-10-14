package com.bobrov.eshop.controller;

import com.bobrov.eshop.mapper.UserMapper;
import com.bobrov.eshop.model.User;
import com.bobrov.eshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/profile")
    public String getUser(Model model, Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        model.addAttribute("user", UserMapper.INSTANCE.toDto(user));

        return "profile";
    }
}
