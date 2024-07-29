package com.example.citymanagement.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"houses", "passport"})
public class PersonDto {
    private int id;
    private String name;
    private BigDecimal bankAccountBalance;
    private PassportDto passport;
    private Set<HouseDto> houses;
}
