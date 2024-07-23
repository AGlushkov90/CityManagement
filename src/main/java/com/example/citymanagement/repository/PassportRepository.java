package com.example.citymanagement.repository;

import com.example.citymanagement.model.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportRepository extends JpaRepository<Passport, Long> {
}
