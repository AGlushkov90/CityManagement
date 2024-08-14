package com.example.carserver.service;

import com.example.carserver.dto.CarDto;
import com.example.carserver.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.jooq.generated.tables.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class CarServiceImpl {

    private final CarRepository carRepository;

    public CarDto saveCar(CarDto carDto) {
        return carRepository.saveCar(carDto);
    }


    public Set<CarDto> getCars() {
        return  carRepository.getCars();
    }


    public CarDto getCar(int id) {
        return carRepository.getCar(id);
    }


    public ResponseEntity<HttpStatus> deleteCar(CarDto carDto) {
        return carRepository.deleteCar(carDto) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    public CarDto updateCar(int id, CarDto carDto) {
        return  carRepository.updateCar(id, carDto);
    }
}
