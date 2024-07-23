package com.example.citymanagement.service;

import com.example.citymanagement.model.House;
import com.example.citymanagement.model.Passport;
import com.example.citymanagement.model.Person;
import com.example.citymanagement.repository.HouseRepository;
import com.example.citymanagement.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class PersonServiceImpl {

    private final PersonRepository personRepository;
    private final HouseRepository houseRepository;

    @Transactional
    public ResponseEntity<Person> savePerson(Person person) {
        try {
            Set<House> houseSet = new HashSet<>();
            findHouse(houseSet, person);
            person.setHouses(houseSet);
            Passport passport = new Passport();
            passport.setNumber(generateNumber());
            person.setPassport(passport);
            return new ResponseEntity<>(personRepository.save(person), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Person> updatePerson(Long id, Person person) {
        Optional<Person> personData = personRepository.findById(id);
        if (personData.isPresent()) {
            Person currentPerson = personData.get();
            currentPerson.setName(person.getName());
            Set<House> houseSet = new HashSet<>();
            findHouse(houseSet, person);
            currentPerson.setHouses(houseSet);
            return new ResponseEntity<>(personRepository.save(currentPerson), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Person>> findAllPersons() {
        return new ResponseEntity<>(personRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Person> findPersonById(Long id) {
        Optional<Person> personData = personRepository.findById(id);
        return personData.map(person -> new ResponseEntity<>(person, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<HttpStatus> deletePerson(Person person) {
        try {
            personRepository.deleteById((long) person.getId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String generateNumber() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(1 + (int) (Math.random() * 9));
        }
        return sb.toString();
    }

    private void findHouse(Set<House> houseSet, Person person) {
        for (House h : person.getHouses()) {
            Optional<House> houseData = houseRepository.findByAddress(h.getAddress());
            if (houseData.isPresent()) {
                houseSet.add(houseData.get());
            } else {
                houseSet.add(h);
            }
        }
    }
}
