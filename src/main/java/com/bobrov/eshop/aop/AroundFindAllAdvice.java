package com.bobrov.eshop.aop;

import com.bobrov.eshop.exception.NotFoundException;
import com.bobrov.eshop.model.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class AroundFindAllAdvice {

    @Around("execution(* com.bobrov.eshop.service.impl.*.findAll(..))")
    public Object checkPossibilityToSaveUser(ProceedingJoinPoint joinPoint) throws Throwable {
        Integer offset = (Integer) joinPoint.getArgs()[0];
        Integer limit = (Integer) joinPoint.getArgs()[1];

        if (offset == null) {
            joinPoint.getArgs()[0] = 0;
        }
        if (limit == null) {
            joinPoint.getArgs()[1] = 5;
        }

        List<User> result = (List<User>) joinPoint.proceed(joinPoint.getArgs());

        if (result.isEmpty()) {
            throw new NotFoundException("The page index must not be greater than last page");
        }

        return result;
    }
}
