package com.example.citymanagementnew.dto;

//import com.example.FeignClient.client.CarDto;
import com.example.FeignClient.client.CarDto;
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
    private Set<CarDto> cars;
}
