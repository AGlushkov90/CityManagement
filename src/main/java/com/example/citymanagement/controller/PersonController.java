package com.example.citymanagement.controller;

import com.example.citymanagement.dto.PersonDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/api/v1/person")
public interface PersonController {
    @PostMapping()
    ResponseEntity<PersonDto> savePerson(@RequestBody PersonDto personDto);

    @GetMapping("/all")
    ResponseEntity<Set<PersonDto>> getPersons();

    @GetMapping("/{id}")
    ResponseEntity<PersonDto> getPerson(@PathVariable("id") Long id);

    @DeleteMapping()
    ResponseEntity<HttpStatus> deletePerson(@RequestBody PersonDto personDto);

    @PutMapping("/{id}")
    ResponseEntity<PersonDto> updatePerson(@PathVariable Long id, @RequestBody PersonDto personDto);
}
