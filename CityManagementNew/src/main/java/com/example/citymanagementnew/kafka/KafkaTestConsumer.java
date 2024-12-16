package com.example.citymanagementnew.kafka;

import com.example.citymanagementnew.service.PersonServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class KafkaTestConsumer {

    private final PersonServiceImpl personService;
    @KafkaListener(topics = {"cancelDeleteCar"}, groupId = "cancelDeleteCar")
    public void test(String message){
        personService.cancelDeletePerson(Integer.parseInt(message));
    }
}
