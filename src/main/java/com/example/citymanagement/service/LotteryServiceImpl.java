package com.example.citymanagement.service;

import com.example.citymanagement.model.Person;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class LotteryServiceImpl {
    @Scheduled(fixedRate = 5000)
    public void startLottery(){

    }


    public Person  getWinner(){
        return null;
    }
}
