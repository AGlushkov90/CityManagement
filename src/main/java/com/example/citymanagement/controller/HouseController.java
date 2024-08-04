package com.example.citymanagement.controller;

import com.example.citymanagement.dto.HouseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public interface HouseController {
    @PostMapping("/api/v1/house")
    ResponseEntity<HouseDto> saveHouse(@RequestBody HouseDto houseDto);

    @GetMapping("/api/v1/house/all")
    ResponseEntity<Set<HouseDto>> getHouses();

    @GetMapping("/api/v1/house/{id}")
    ResponseEntity<HouseDto> getHouse(@PathVariable("id") Long id);

    @DeleteMapping("/api/v1/house")
    ResponseEntity<HttpStatus> deleteHouse(@RequestBody HouseDto houseDto);

    @PutMapping("/api/v1/house/{id}")
    ResponseEntity<HouseDto> updateHouse(@PathVariable("id") Long id, @RequestBody HouseDto houseDto);
}
