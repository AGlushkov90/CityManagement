package com.example.citymanagement.controller;

import com.example.citymanagement.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/person")
public interface PersonController {
    @PostMapping()
    Person savePerson(Person person);
    @GetMapping()
    List<Person> getPersons();

}
