package com.example.citymanagementnew.controller;

import com.example.citymanagementnew.dto.PassportDto;
import com.example.citymanagementnew.dto.PersonDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public interface PersonController {
    @PostMapping("/api/v1/person")
    ResponseEntity<PersonDto> savePerson(@RequestBody PersonDto personDto);

    @GetMapping("/api/v1/person/all")
    ResponseEntity<Set<PersonDto>> getPersons();

    // TODO Как передавать параметры аналогичные street
    @GetMapping("/api/v1/person/findByStreet/{street}")
    ResponseEntity<Set<PersonDto>> findByStreet(@PathVariable("street") String street);

    @GetMapping("/api/v1/person/findByLetter/{letter}")
    ResponseEntity<Set<PassportDto>> findByLetter(@PathVariable("letter") String letter);

    @GetMapping("/api/v1/person/{id}")
    ResponseEntity<PersonDto> getPerson(@PathVariable("id") Long id);

    @DeleteMapping("/api/v1/person")
    ResponseEntity<HttpStatus> deletePerson(@RequestBody PersonDto personDto);

    @PutMapping("/api/v1/person/{id}")
    ResponseEntity<PersonDto> updatePerson(@PathVariable("id") Long id, @RequestBody PersonDto personDto);
}
