package com.example.citymanagementnew.controller;

import com.example.citymanagementnew.dto.HouseDto;
import com.example.citymanagementnew.dto.HouseMapper;
import com.example.citymanagementnew.service.HouseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
public class HouseControllerImpl implements HouseController {

    private final HouseServiceImpl houseServiceImpl;
    private final HouseMapper houseMapper;

    @Override
    public ResponseEntity<HouseDto> saveHouse(HouseDto houseDto) {
        return new ResponseEntity<>(houseMapper.toHouseDto(houseServiceImpl.saveHouse(houseMapper.toHouse(houseDto))), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Set<HouseDto>> getHouses() {
        return new ResponseEntity<>(houseMapper.toHouseDtoList(houseServiceImpl.findAllHouses()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HouseDto> getHouse(Long id) {
        return new ResponseEntity<>(houseMapper.toHouseDto(houseServiceImpl.findHouseById(id)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteHouse(HouseDto houseDto) {
        return houseServiceImpl.deleteHouse(houseMapper.toHouse(houseDto));
    }

    @Override
    public ResponseEntity<HouseDto> updateHouse(Long id, HouseDto houseDto) {
        return new ResponseEntity<>(houseMapper.toHouseDto(houseServiceImpl.updateHouse(id, houseMapper.toHouse(houseDto))), HttpStatus.OK);
    }
}
