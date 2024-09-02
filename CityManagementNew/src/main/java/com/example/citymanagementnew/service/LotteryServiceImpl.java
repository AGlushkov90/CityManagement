package com.example.citymanagementnew.service;

import com.example.citymanagementnew.aspect.LotteryCheater;
import com.example.citymanagementnew.kafka.KafkaTestProducer;
import com.example.citymanagementnew.model.Person;
import com.example.citymanagementnew.utils.GenerateNumber;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
@AllArgsConstructor
public class LotteryServiceImpl {

    private KafkaTestProducer kafkaTestProducer;
    private final PersonServiceImpl personService;
//    @Scheduled(fixedRate = 5000)
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

//    @Scheduled(fixedRate = 5000)
//    public void testKafka(){
//        kafkaTestProducer.sendMessage();
//    }
}
