package com.example.citymanagement.controller;

import com.example.citymanagement.dto.PersonDto;
import com.example.citymanagement.dto.PersonMapper;
import com.example.citymanagement.service.PersonServiceImpl;
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

    @Override
    public ResponseEntity<PersonDto> savePerson(PersonDto personDto) {
        return new ResponseEntity<>(personMapper.toPersonDto(personServiceImpl.savePerson(personMapper.toPerson(personDto))), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Set<PersonDto>> getPersons() {
        return new ResponseEntity<>(personMapper.toPersonDtoList(personServiceImpl.findAllPersons()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PersonDto> getPerson(Long id) {
        return new ResponseEntity<>(personMapper.toPersonDto(personServiceImpl.findPersonById(id)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> deletePerson(PersonDto personDto) {
        return personServiceImpl.deletePerson(personMapper.toPerson(personDto));
    }

    @Override
    public ResponseEntity<PersonDto> updatePerson(Long id, PersonDto personDto) {
        return new ResponseEntity<>(personMapper.toPersonDto(personServiceImpl.updatePerson(id, personMapper.toPerson(personDto))), HttpStatus.OK);
    }
}
