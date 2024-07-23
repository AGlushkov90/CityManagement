package com.example.citymanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(exclude="person")
@ToString(exclude="person")
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String number;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "passport")
    @JsonIgnore
    private Person person;
}
