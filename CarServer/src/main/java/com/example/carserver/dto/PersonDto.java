package com.example.carserver.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"cars"})
public class PersonDto {
    private int id;
    private String name;
    private Set<CarDto> cars;
}
