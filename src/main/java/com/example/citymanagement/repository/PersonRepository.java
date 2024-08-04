package com.example.citymanagement.repository;

import com.example.citymanagement.model.Person;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    // TODO надо преопределять методы в репозитории, на уровне сервиса никак не использовать?
    @EntityGraph(attributePaths = {"houses", "passport"})
    List<Person> findAll();
}
