package com.example.citymanagement.service;

import com.example.citymanagement.exception.EntityNotCreatedException;
import com.example.citymanagement.exception.EntityNotDeletedException;
import com.example.citymanagement.exception.EntityNotFoundException;
import com.example.citymanagement.exception.EntityNotUpdatedException;
import com.example.citymanagement.model.House;
import com.example.citymanagement.model.Passport;
import com.example.citymanagement.model.Person;
import com.example.citymanagement.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.*;

@Service
@AllArgsConstructor
public class PersonServiceImpl {

    private final PersonRepository personRepository;
    private final HouseServiceImpl  houseService;
    private final PassportServiceImpl passportService;
    private final TransactionTemplate transactionTemplate;


    //    @Transactional()
    public Person savePerson(Person person) {

        try {
            return transactionTemplate.execute(status -> {
                Set<House> houseSet = new HashSet<>();
                if (person.getHouses() != null)
                {
                    findHouse(houseSet, person);
                    person.setHouses(houseSet);
                }
                Passport passport = passportService.savePassport();
                person.setPassport(passport);
                return personRepository.save(person);
            });
        } catch (Exception e) {
            throw new EntityNotCreatedException("Person not created");
        }
    }

    @Transactional
    public Person updatePerson(Long id, Person person) {
        try {
        Optional<Person> personData = personRepository.findById(id);
        if (personData.isPresent()) {
            Person currentPerson = personData.get();
            currentPerson.setName(person.getName());
            currentPerson.setBankAccountBalance(person.getBankAccountBalance());
            Set<House> houseSet = new HashSet<>();
            findHouse(houseSet, person);
            currentPerson.setHouses(houseSet);
            return personRepository.save(currentPerson);
        } else {
            throw new EntityNotFoundException("Person not found", id);
        }
        } catch (Exception e) {
            throw new EntityNotUpdatedException("Person not updated", id);
        }
    }

    public Set<Person> findAllPersons() {
        return new HashSet<>(personRepository.findAll());
    }

    public Set<Person> findByStreet(String street){
        return new HashSet<>(personRepository.findByStreet(street));
    }

    public Set<Passport> findAllPassportsByPersonName(String letter){
        return new HashSet<>(passportService.findAllPassportsByPersonName(letter));
    }

    public Person findPersonById(Long id) {
        Optional<Person> personData = personRepository.findById(id);
        return personData.
                orElseThrow(() -> new EntityNotFoundException("Person not found ", id));
    }

     @Transactional
    public ResponseEntity<HttpStatus> deletePerson(Person person) {
        long personId = person.getId();
        try {
            personRepository.deleteById(personId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new EntityNotDeletedException("Person not deleted", personId);
        }
    }

    private void findHouse(Set<House> houseSet, Person person) {
        for (House h : person.getHouses()) {
            House houseData = houseService.findByAddress(h.getAddress());
            houseSet.add(Objects.requireNonNullElse(houseData, h));
        }
    }
}
