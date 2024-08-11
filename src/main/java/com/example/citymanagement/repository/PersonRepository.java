package com.example.citymanagement.repository;

import com.example.citymanagement.model.Person;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @EntityGraph(attributePaths = {"houses", "passport"})
    List<Person> findAll();

    @Query("select distinct p from Person p join p.houses h where h.street = :street")
    List<Person> findByStreet(String street);



}
