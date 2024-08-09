package com.example.citymanagement.service;

import com.example.citymanagement.exception.EntityNotCreatedException;
import com.example.citymanagement.exception.EntityNotDeletedException;
import com.example.citymanagement.exception.EntityNotFoundException;
import com.example.citymanagement.exception.EntityNotUpdatedException;
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
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class HouseServiceImpl {

    private final PersonRepository personRepository;
    private final HouseRepository houseRepository;

    @Transactional
    public House saveHouse(House house) {
        try {
            if(house.getPersons() != null) {
                Set<Person> personSet = new HashSet<>();
                findHouse(personSet, house);
                house.setPersons(personSet);
            }
            return houseRepository.save(house);
        } catch (Exception e) {
            throw new EntityNotCreatedException("House not created");
        }
    }

    @Transactional
    public House updateHouse(Long id, House house) {
        try {
        Optional<House> houseData = houseRepository.findById(id);
        if (houseData.isPresent()) {
            House currenthouse = houseData.get();
            currenthouse.setAddress(house.getAddress());
            Set<Person> personSet = new HashSet<>();
            findHouse(personSet, house);
            currenthouse.setPersons(personSet);
            return houseRepository.save(currenthouse);
        } else {
            throw new EntityNotFoundException("House not found", id);
        }
        } catch (Exception e) {
            throw new EntityNotUpdatedException("House not updated", id);
        }
    }

    public Set<House> findAllHouses() {
        return new HashSet<>(houseRepository.findAll());
    }

    public House findHouseById(Long id) {
        Optional<House> houseData = houseRepository.findById(id);
        return houseData.
                orElseThrow(() -> new EntityNotFoundException("House not found ", id));
    }

    @Transactional
    public ResponseEntity<HttpStatus> deleteHouse(House house) {
        long houseId = house.getId();
        try {
            houseRepository.deleteById(houseId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new EntityNotDeletedException("House not deleted", houseId);
                }
    }

    public House findByAddress(String Address){
        return houseRepository.findByAddress(Address).orElse(null);
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
