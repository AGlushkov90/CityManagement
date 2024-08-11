package com.example.citymanagement.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = "persons")
@ToString(exclude = "persons")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String address;
    private String street;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "house_person",
            joinColumns = {@JoinColumn(name = "house_id")},
            inverseJoinColumns = {@JoinColumn(name = "person_id")}
    )
    private Set<Person> persons;
}
