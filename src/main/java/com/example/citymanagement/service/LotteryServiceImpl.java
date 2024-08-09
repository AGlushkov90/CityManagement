package com.example.citymanagement.service;

import com.example.citymanagement.aspect.LotteryCheater;
import com.example.citymanagement.model.Person;
import com.example.citymanagement.utils.GenerateNumber;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
@AllArgsConstructor
public class LotteryServiceImpl {

    private final PersonServiceImpl personService;
    @Scheduled(fixedRate = 5000)
    public void startLottery(){
        Set<Person> persons = personService.findAllPersons();
        if(persons.isEmpty()){
            return;
        }
        Person person = getWinner(persons);
        BigDecimal bankAccountBalance = person.getBankAccountBalance() == null ? BigDecimal.ZERO : person.getBankAccountBalance();
        person.setBankAccountBalance(bankAccountBalance.add(BigDecimal.valueOf(1000)));
        personService.updatePerson((long) person.getId(), person);
        System.out.println(person);
    }

    @LotteryCheater
    private Person getWinner(Set<Person> persons){
        int numberOfHappyPerson = GenerateNumber.generateNumber(persons.size());
        return (Person) persons.toArray()[numberOfHappyPerson];
    }
}
