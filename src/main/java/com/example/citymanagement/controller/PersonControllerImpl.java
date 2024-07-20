package com.example.citymanagement.controller;

import com.example.citymanagement.model.Person;
import com.example.citymanagement.service.PersonServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class PersonControllerImpl implements PersonController{

    private PersonServiceImpl personServiceImpl;
    @Override
    public Person savePerson(Person person) {
        return personServiceImpl.savePerson(person);
    }

    @Override
    public List<Person> getPersons() {
        return personServiceImpl.findAllPersons();
    }
}
