package com.example.citymanagement.repository;

import com.example.citymanagement.model.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PassportRepository extends JpaRepository<Passport, Long> {
    @Query(value = "select p.id, number from passport p join person pe on p.id = pe.passport_id where pe.name LIKE Concat(:letter, '%')", nativeQuery = true)
    List<Passport> findAllPassportsByPersonName(@Param("letter") String letter);
}
