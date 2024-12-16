package com.example.carserver.controller;

import com.example.carserver.dto.CarDto;
import com.example.carserver.dto.PersonDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public interface CarController {
    @PostMapping("/api/v1/car")
    ResponseEntity<CarDto> saveCar(@RequestBody CarDto carDto);

    @GetMapping("/api/v1/car/all")
    ResponseEntity<Set<CarDto>> getCars();

    @GetMapping("/api/v1/car/{id}")
    ResponseEntity<CarDto> getCar(@PathVariable("id") int id);

    @DeleteMapping("/api/v1/car")
    ResponseEntity<HttpStatus> deleteCar(@RequestBody CarDto carDto);

    @PutMapping("/api/v1/car/{id}")
    ResponseEntity<CarDto> updateCar(@PathVariable("id") int id, @RequestBody CarDto carDto);

    // TODO нормальный выбор метода и параметров?
    @PostMapping("/api/v1/car/setOwner/{id}")
    ResponseEntity<CarDto> setOwner(@PathVariable("id") int id, @RequestBody PersonDto personDto);
}
