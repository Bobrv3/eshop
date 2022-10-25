package com.bobrov.eshop.aop;

import com.bobrov.eshop.exception.NotFoundException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class FindAllAdvice {
    private static final int OFFSET = 0;
    private static final int LIMIT = 5;

    @Around("execution(* com.bobrov.eshop.service.impl.*.findAll(..))")
    public Object checkPossibilityToSaveUser(ProceedingJoinPoint joinPoint) throws Throwable {
        Integer offset = (Integer) joinPoint.getArgs()[0];
        Integer limit = (Integer) joinPoint.getArgs()[1];

        if (offset == null) {
            joinPoint.getArgs()[0] = OFFSET;
        }
        if (limit == null) {
            joinPoint.getArgs()[1] = LIMIT;
        }

        List<Object> result = (List<Object>) joinPoint.proceed(joinPoint.getArgs());

        if (result.isEmpty()) {
            throw new NotFoundException("The page index must not be greater than last page");
        }

        return result;
    }
}
