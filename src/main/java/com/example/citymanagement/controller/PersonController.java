package com.example.citymanagement.controller;

import com.example.citymanagement.dto.PersonDto;
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

    @GetMapping("/api/v1/person/{id}")
    ResponseEntity<PersonDto> getPerson(@PathVariable("id") Long id);

    @DeleteMapping("/api/v1/person")
    ResponseEntity<HttpStatus> deletePerson(@RequestBody PersonDto personDto);

    @PutMapping("/api/v1/person/{id}")
    ResponseEntity<PersonDto> updatePerson(@PathVariable("id") Long id, @RequestBody PersonDto personDto);
}
