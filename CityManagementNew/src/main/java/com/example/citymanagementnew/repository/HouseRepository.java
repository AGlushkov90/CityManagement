package com.example.citymanagementnew.repository;

import com.example.citymanagementnew.model.House;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HouseRepository extends JpaRepository<House, Long> {
    Optional<House> findByAddress(String address);
}
