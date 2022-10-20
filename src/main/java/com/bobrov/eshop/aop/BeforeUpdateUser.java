package com.bobrov.eshop.aop;

import com.bobrov.eshop.dao.UserRepository;
import com.bobrov.eshop.dto.request.UserRequest;
import com.bobrov.eshop.exception.NotFoundException;
import com.bobrov.eshop.model.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
public class BeforeUpdateUser {
    @Autowired
    private UserRepository userRepository;

    @Before("execution(* com.bobrov.eshop.service.impl.UserImpl.update(..))")
    public void checkPossibilityToUpdateUser(JoinPoint joinPoint) {
        UserRequest userRequest = (UserRequest) joinPoint.getArgs()[0];

        try {
            User user = userRepository.findByUsername(userRequest.getUsername())
                    .orElseThrow(NotFoundException::new);

            if (!Objects.equals(user.getId(), userRequest.getId())) {
                throw new RuntimeException("user with such username's already exists");
            } else {
                checkPasswords(userRequest);
            }
        } catch (NotFoundException ex) {
            checkPasswords(userRequest);
        }
    }

    private static void checkPasswords(UserRequest userRequest) {
        if (!userRequest.getPassword().equals(userRequest.getRepeatPassword())) {
            throw new RuntimeException("passwords don't match");
        }
    }
}
