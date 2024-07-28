package com.example.citymanagement.aspect;

import com.example.citymanagement.model.Person;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LotteryAspect {
    @After(value = "@annotation(com.example.citymanagement.aspect.LotteryChiter)")
    @SneakyThrows
    public Object checkWinner(ProceedingJoinPoint joinPoint)
    {
        Person person = (Person) joinPoint.proceed();
        return null;
    }
}
