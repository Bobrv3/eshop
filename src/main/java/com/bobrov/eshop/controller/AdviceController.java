package com.bobrov.eshop.controller;

import com.bobrov.eshop.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class AdviceController {
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomException process(RuntimeException e) {
        return CustomException.builder()
                .message(e.getMessage())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
