package com.example.citymanagementnew.controller;

//import com.example.FeignClient.client.CarDto;
//import com.example.citymanagement.client.CarClient;
import com.example.FeignClient.client.CarDto;
import com.example.citymanagementnew.client.CarClient;
import com.example.citymanagementnew.dto.PassportDto;
import com.example.citymanagementnew.dto.PassportMapper;
import com.example.citymanagementnew.dto.PersonDto;
import com.example.citymanagementnew.dto.PersonMapper;
import com.example.citymanagementnew.service.PersonServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
public class PersonControllerImpl implements PersonController {

    private final PersonServiceImpl personServiceImpl;
    private final PersonMapper personMapper;
    private final PassportMapper passportMapper;
     private final CarClient carClient;

    @Override
    public ResponseEntity<PersonDto> savePerson(PersonDto personDto) {
        return new ResponseEntity<>(personMapper.toPersonDto(personServiceImpl.savePerson(personMapper.toPerson(personDto))), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Set<PersonDto>> getPersons() {
        return new ResponseEntity<>(personMapper.toPersonDtoList(personServiceImpl.findAllPersons()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Set<PersonDto>> findByStreet(String street) {
        return new ResponseEntity<>(personMapper.toPersonDtoList(personServiceImpl.findByStreet(street)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Set<PassportDto>> findByLetter(String letter) {
        return new ResponseEntity<>(passportMapper.toPassportDtoList(personServiceImpl.findAllPassportsByPersonName(letter)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PersonDto> getPerson(Long id) {
        return new ResponseEntity<>(personMapper.toPersonDto(personServiceImpl.findPersonById(id)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> deletePerson(PersonDto personDto) {
        for (CarDto carDto : personDto.getCars()) {
            carClient.deleteCar(carDto);
        }
        return personServiceImpl.deletePerson(personMapper.toPerson(personDto));
    }

    @Override
    public ResponseEntity<PersonDto> updatePerson(Long id, PersonDto personDto) {
        return new ResponseEntity<>(personMapper.toPersonDto(personServiceImpl.updatePerson(id, personMapper.toPerson(personDto))), HttpStatus.OK);
    }
}
