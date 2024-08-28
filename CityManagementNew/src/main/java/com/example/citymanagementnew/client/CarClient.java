package com.example.citymanagementnew.client;

import com.example.FeignClient.client.CarController;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;


@FeignClient(name = "CarServer")
@Component
public interface CarClient extends CarController {

}
