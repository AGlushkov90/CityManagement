package com.example.citymanagement.dto;

import com.example.citymanagement.model.Passport;
import lombok.Data;

import java.util.Set;

@Data
public class PersonDto {
    private int id;
    private String name;
    private Passport passport;
    private Set<HouseDto> houses;
}
