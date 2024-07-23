package com.example.citymanagement.service;

import com.example.citymanagement.model.House;
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
public class HouseServiceImpl {

    private final PersonRepository personRepository;
    private final HouseRepository houseRepository;

    @Transactional
    public ResponseEntity<House> saveHouse(House house) {
        try {
            Set<Person> personSet = new HashSet<>();
            findHouse(personSet, house);
            house.setPersons(personSet);
            return new ResponseEntity<>(houseRepository.save(house), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<House> updateHouse(Long id, House house) {
        Optional<House> houseData = houseRepository.findById(id);
        if (houseData.isPresent()) {
            House currenthouse = houseData.get();
            currenthouse.setAddress(house.getAddress());
            Set<Person> personSet = new HashSet<>();
            findHouse(personSet, house);
            currenthouse.setPersons(personSet);
            return new ResponseEntity<>(houseRepository.save(currenthouse), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<House>> findAllHouses() {
        return new ResponseEntity<>(houseRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<House> findHouseById(Long id) {
        Optional<House> houseData = houseRepository.findById(id);
        return houseData.map(house -> new ResponseEntity<>(house, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<HttpStatus> deleteHouse(House house) {
        try {
            houseRepository.deleteById((long) house.getId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void findHouse(Set<Person> personSet, House house) {
        for (Person p : house.getPersons()) {
            Optional<Person> personData = personRepository.findById((long) p.getId());
            if (personData.isPresent()) {
                personSet.add(personData.get());
            } else {
                p.setId(0);
                personSet.add(p);
            }
        }
    }
}
