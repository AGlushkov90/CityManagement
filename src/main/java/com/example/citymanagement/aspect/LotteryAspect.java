package com.example.citymanagement.aspect;

import com.example.citymanagement.model.Person;
import com.example.citymanagement.utils.GenerateNumber;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@Aspect
@AllArgsConstructor
public class LotteryAspect {
    @After(value = "@annotation(com.example.citymanagement.aspect.LotteryCheater)")
    @SneakyThrows
    public Object checkWinner(ProceedingJoinPoint joinPoint)
    {
       Person person = (Person) joinPoint.proceed();
        if (person.getHouses().isEmpty()){
            return person;
        }
        Object[] arguments = joinPoint.getArgs();
        for (Object arg : arguments) {
            if (arg instanceof Set<?>){
                Set<Person> persons = ((Set<Person>) arg).stream().filter(p -> p.getHouses().isEmpty()).collect(Collectors.toSet());
                int numberOfHappyPerson = GenerateNumber.generateNumber(persons.size());
                return persons.toArray()[numberOfHappyPerson];
            }
        }
        return person;
    }
}
