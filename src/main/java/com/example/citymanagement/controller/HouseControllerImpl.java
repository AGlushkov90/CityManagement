package com.example.citymanagement.controller;

import com.example.citymanagement.model.House;
import com.example.citymanagement.service.HouseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class HouseControllerImpl implements HouseController {

    private HouseServiceImpl houseServiceImpl;

    @Override
    public ResponseEntity<House> saveHouse(House house) {
        return houseServiceImpl.saveHouse(house);
    }

    @Override
    public ResponseEntity<List<House>> getHouses() {
        return houseServiceImpl.findAllHouses();
    }

    @Override
    public ResponseEntity<House> getHouse(Long id) {
        return houseServiceImpl.findHouseById(id);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteHouse(House house) {
        return houseServiceImpl.deleteHouse(house);
    }

    @Override
    public ResponseEntity<House> updateHouse(@PathVariable Long id, @RequestBody House house) {
        return houseServiceImpl.updateHouse(id, house);
    }
}
