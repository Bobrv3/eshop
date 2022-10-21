package com.bobrov.eshop.aop;

import com.bobrov.eshop.dto.OrderDto;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BeforeSaveOrUpdateOrderAdvice {
    @Before("execution(* com.bobrov.eshop.service.impl.OrderImpl.save(..)) || execution(* com.bobrov.eshop.service.impl.OrderImpl.update(..))")
    public void checkPossibilityToSaveUser(JoinPoint joinPoint) {
        OrderDto orderDto = (OrderDto) joinPoint.getArgs()[0];

        if (orderDto.getOrderDetails() == null || orderDto.getOrderDetails().isEmpty()) {
            throw new RuntimeException("You cannot create an order without a list of products");
        } else if (orderDto.getUser() == null || orderDto.getUser().getUsername() == null) {
            throw new RuntimeException("You cannot create an order without a user");
        }
    }
}
