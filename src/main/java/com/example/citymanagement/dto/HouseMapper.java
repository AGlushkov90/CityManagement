package com.example.citymanagement.dto;

import com.example.citymanagement.model.House;
import com.example.citymanagement.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)

public interface HouseMapper {
    @Mapping(target = "persons", ignore = true)
    HouseDto toHouseDto(House house);
    House toHouse(HouseDto houseDto);

    Set<PersonDto> toPersonDtoList(Set<Person> personList);
    Set<Person> toPersonList(Set<PersonDto> personDtoList);
}
