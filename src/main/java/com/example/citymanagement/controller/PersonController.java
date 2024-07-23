package com.example.citymanagement.controller;

import com.example.citymanagement.model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/person")
public interface PersonController {
    @PostMapping()
    ResponseEntity<Person> savePerson(@RequestBody Person person);

    @GetMapping("/all")
    ResponseEntity<List<Person>> getPersons();

    @GetMapping("/{id}")
    ResponseEntity<Person> getPerson(@PathVariable("id") Long id);

    @DeleteMapping()
    ResponseEntity<HttpStatus> deletePerson(@RequestBody Person person);

    @PutMapping("/{id}")
    ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person);
}
