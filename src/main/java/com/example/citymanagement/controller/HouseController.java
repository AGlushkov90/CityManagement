package com.example.citymanagement.controller;

import com.example.citymanagement.model.House;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/house")
public interface HouseController {
    @PostMapping()
    ResponseEntity<House> saveHouse(@RequestBody House house);

    @GetMapping("/all")
    ResponseEntity<List<House>> getHouses();

    @GetMapping("/{id}")
    ResponseEntity<House> getHouse(@PathVariable("id") Long id);

    @DeleteMapping()
    ResponseEntity<HttpStatus> deleteHouse(@RequestBody House house);

    @PutMapping("/{id}")
    ResponseEntity<House> updateHouse(@PathVariable Long id, @RequestBody House house);
}
