package com.example.citymanagement.dto;

import com.example.citymanagement.model.House;
import com.example.citymanagement.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonMapper {
    PersonDto toPersonDto(Person person);
    Person toPerson(PersonDto personDto);
    @Mapping(target = "persons", ignore = true)
    HouseDto toHouseDto(House house);

    Set<HouseDto> toHouseDtoList(Set<House> houseList);
    Set<House> toHouseList(Set<HouseDto> houseDtoList);
}
