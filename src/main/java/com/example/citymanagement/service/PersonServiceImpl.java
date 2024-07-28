package com.example.citymanagement.service;

import com.example.citymanagement.dto.HouseMapper;
import com.example.citymanagement.dto.PersonDto;
import com.example.citymanagement.dto.PersonMapper;
import com.example.citymanagement.exception.EntityNotFoundException;
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
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class PersonServiceImpl {

    private final PersonRepository personRepository;
    private final HouseRepository houseRepository;
    private final PersonMapper personMapper;
    private final HouseMapper houseMapper;

    @Transactional
    public ResponseEntity<PersonDto> savePerson(Person person) {
        try {
            Set<House> houseSet = new HashSet<>();
            findHouse(houseSet, person);
            person.setHouses(houseSet);
            Passport passport = new Passport();
            passport.setNumber(generateNumber());
            person.setPassport(passport);
            return new ResponseEntity<>(personMapper.toPersonDto(personRepository.save(person)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<PersonDto> updatePerson(Long id, Person person) {
        try {
        Optional<Person> personData = personRepository.findById(id);
        if (personData.isPresent()) {
            Person currentPerson = personData.get();
            currentPerson.setName(person.getName());
            Set<House> houseSet = new HashSet<>();
            findHouse(houseSet, person);
            currentPerson.setHouses(houseSet);
            return new ResponseEntity<>(personMapper.toPersonDto(personRepository.save(currentPerson)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Set<PersonDto>> findAllPersons() {
        return new ResponseEntity<>(houseMapper.toPersonDtoList(new HashSet<>(personRepository.findAll())), HttpStatus.OK);
    }

    public ResponseEntity<PersonDto> findPersonById(Long id) {
        Optional<Person> personData = personRepository.findById(id);
        return personData.map(person -> new ResponseEntity<>(personMapper.toPersonDto(person), HttpStatus.OK)).
                orElseThrow(() -> new EntityNotFoundException("Person not found ", id));
    }

    @Transactional
    public ResponseEntity<HttpStatus> deletePerson(PersonDto personDto) {
        try {
            personRepository.deleteById((long) personDto.getId());
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
