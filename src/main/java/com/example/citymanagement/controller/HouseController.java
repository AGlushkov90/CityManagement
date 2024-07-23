package com.example.citymanagement.controller;

import com.example.citymanagement.dto.HouseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/api/v1/house")
public interface HouseController {
    @PostMapping()
    ResponseEntity<HouseDto> saveHouse(@RequestBody HouseDto houseDto);

    @GetMapping("/all")
    ResponseEntity<Set<HouseDto>> getHouses();

    @GetMapping("/{id}")
    ResponseEntity<HouseDto> getHouse(@PathVariable("id") Long id);

    @DeleteMapping()
    ResponseEntity<HttpStatus> deleteHouse(@RequestBody HouseDto houseDto);

    @PutMapping("/{id}")
    ResponseEntity<HouseDto> updateHouse(@PathVariable Long id, @RequestBody HouseDto houseDto);
}
