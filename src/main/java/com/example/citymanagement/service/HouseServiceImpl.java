package com.example.citymanagement.service;

import com.example.citymanagement.dto.HouseDto;
import com.example.citymanagement.dto.HouseMapper;
import com.example.citymanagement.dto.PersonMapper;
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
    private final HouseMapper houseMapper;
    private final PersonMapper personMapper;

    @Transactional
    public ResponseEntity<HouseDto> saveHouse(House house) {
        try {
            Set<Person> personSet = new HashSet<>();
            findHouse(personSet, house);
            house.setPersons(personSet);
            return new ResponseEntity<>(houseMapper.toHouseDto(houseRepository.save(house)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<HouseDto> updateHouse(Long id, House house) {
        try {
        Optional<House> houseData = houseRepository.findById(id);
        if (houseData.isPresent()) {
            House currenthouse = houseData.get();
            currenthouse.setAddress(house.getAddress());
            Set<Person> personSet = new HashSet<>();
            findHouse(personSet, house);
            currenthouse.setPersons(personSet);
            return new ResponseEntity<>(houseMapper.toHouseDto(houseRepository.save(currenthouse)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Set<HouseDto>> findAllHouses() {
        return new ResponseEntity<>(personMapper.toHouseDtoList(new HashSet<>(houseRepository.findAll())), HttpStatus.OK);
    }

    public ResponseEntity<HouseDto> findHouseById(Long id) {
        Optional<House> houseData = houseRepository.findById(id);
        return houseData.map(house -> new ResponseEntity<>(houseMapper.toHouseDto(house), HttpStatus.OK)).
                orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Transactional
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
