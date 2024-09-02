package com.example.carserver.kafka;

import com.example.carserver.service.CarServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class KafkaTestConsumer {

    private final CarServiceImpl carService;
    @KafkaListener(topics = {"deleteCar"}, groupId = "deleteCar")
    public void test(String message){
        carService.deleteCarsByPersonId(Integer.parseInt(message));
    }
}
