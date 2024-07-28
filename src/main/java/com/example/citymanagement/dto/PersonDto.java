package com.example.citymanagement.dto;

import com.example.citymanagement.model.Passport;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"houses", "passport"})
public class PersonDto {
    private int id;
    private String name;
    private PassportDto passport;
    private Set<HouseDto> houses;
}
