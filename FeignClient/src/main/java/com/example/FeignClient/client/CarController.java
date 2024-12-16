package com.example.FeignClient.client;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public interface CarController {
    @DeleteMapping("api/v1/car")
    ResponseEntity<HttpStatus> deleteCar(@RequestBody CarDto carDto);

}
