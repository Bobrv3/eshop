package com.bobrov.eshop.controller;

import com.bobrov.eshop.dto.UserDto;
import com.bobrov.eshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registrate")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;


    @GetMapping
    public String regPage(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);

        return "registration";
    }

    @PostMapping
    public String save(@ModelAttribute UserDto userDto) {
        userService.save(userDto);

        return "redirect:/login";
    }
}
