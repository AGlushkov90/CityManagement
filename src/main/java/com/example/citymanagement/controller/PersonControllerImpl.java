package com.example.citymanagement.controller;

import com.example.citymanagement.model.Person;
import com.example.citymanagement.service.PersonServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class PersonControllerImpl implements PersonController {

    private PersonServiceImpl personServiceImpl;

    @Override
    public ResponseEntity<Person> savePerson(Person person) {
        return personServiceImpl.savePerson(person);
    }

    @Override
    public ResponseEntity<List<Person>> getPersons() {
        return personServiceImpl.findAllPersons();
    }

    @Override
    public ResponseEntity<Person> getPerson(Long id) {
        return personServiceImpl.findPersonById(id);
    }

    @Override
    public ResponseEntity<HttpStatus> deletePerson(Person person) {
        return personServiceImpl.deletePerson(person);
    }

    @Override
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        return personServiceImpl.updatePerson(id, person);
    }
}
