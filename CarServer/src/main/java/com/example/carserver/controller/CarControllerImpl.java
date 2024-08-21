package com.example.carserver.controller;

import com.example.carserver.dto.CarDto;
import com.example.carserver.dto.PersonDto;
import com.example.carserver.service.CarServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
public class CarControllerImpl implements CarController {

    private final CarServiceImpl carService;

    @Override
    public ResponseEntity<CarDto> saveCar(CarDto carDto) {
        return new ResponseEntity<>(carService.saveCar(carDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Set<CarDto>> getCars() {
        return new ResponseEntity<>(carService.getCars(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CarDto> getCar(int id) {
        return new ResponseEntity<>(carService.getCar(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteCar(CarDto carDto) {
        return carService.deleteCar(carDto);
    }

    @Override
    public ResponseEntity<CarDto> updateCar(int id, CarDto carDto) {
        return new ResponseEntity<>(carService.updateCar(id, carDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CarDto> setOwner(int id, PersonDto personDto) {
        return new ResponseEntity<>(carService.setOwner(id, personDto), HttpStatus.OK);
    }
}
