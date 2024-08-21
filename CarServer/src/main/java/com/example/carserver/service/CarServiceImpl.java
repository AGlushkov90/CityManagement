package com.example.carserver.service;

import com.example.carserver.dto.CarDto;
import com.example.carserver.dto.PersonDto;
import com.example.carserver.repository.CarRepository;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Set;

@Service
//@AllArgsConstructor
public class CarServiceImpl {

    private final CarRepository carRepository;
    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;

    public CarServiceImpl(CarRepository carRepository, DiscoveryClient discoveryClient, RestClient.Builder restClientBuilder) {
        this.carRepository = carRepository;
        this.discoveryClient = discoveryClient;
        this.restClient = restClientBuilder.build();
    }

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

    // TODO при вызове restclient при несуществующем id выбрасывается исключение. Как правильно?
    public CarDto setOwner(int id, PersonDto personDto) {
        ServiceInstance  serviceInstance = discoveryClient.getInstances("CITYMANAGEMENT").get(0);
        ResponseEntity<PersonDto> result = restClient.get()
                .uri(serviceInstance.getUri() + "/api/v1/person/{id}", personDto.getId())
                .retrieve()
                .toEntity(PersonDto.class);
        if (result.getStatusCode() == HttpStatus.OK) {
            CarDto carDto = carRepository.getCar(id);
            carDto.setPerson_id(personDto.getId());
            return carRepository.updateCar(id, carDto);
        }
        throw new RuntimeException("Person not found");
    }
}
