package com.example.carserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class CarServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarServerApplication.class, args);
    }

    @Bean(initMethod="init")
    public AllStrategiesExampleBean initMethodExampleBean() {
        return new AllStrategiesExampleBean();
    }
}
