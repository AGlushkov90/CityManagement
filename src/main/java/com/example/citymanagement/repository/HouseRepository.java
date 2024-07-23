package com.example.citymanagement.repository;

import com.example.citymanagement.model.House;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HouseRepository extends JpaRepository<House, Long> {
    Optional<House> findByAddress(String address);
}
