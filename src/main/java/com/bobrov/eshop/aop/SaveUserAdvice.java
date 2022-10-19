package com.bobrov.eshop.aop;

import com.bobrov.eshop.dao.UserRepository;
import com.bobrov.eshop.dto.request.UserRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SaveUserAdvice {
    @Autowired
    private UserRepository userRepository;

    @Before("execution(* com.bobrov.eshop.service.impl.UserImpl.save(..))")
    public void checkPossibilityToSaveUser(JoinPoint joinPoint) {
        UserRequest userRequest = (UserRequest) joinPoint.getArgs()[0];

        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new RuntimeException("such user's already exists");
        } else if (!userRequest.getPassword().equals(userRequest.getRepeatPassword())) {
            throw new RuntimeException("passwords don't match");
        }
    }
}
