package com.example.citymanagement.dto;

import com.example.citymanagement.model.House;
import com.example.citymanagement.model.Passport;
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
    @Mapping(target = "person", ignore = true)
    PassportDto toPassportDto(Passport passport);

    Set<PersonDto> toPersonDtoList(Set<Person> personList);
    Set<Person> toPersonList(Set<PersonDto> personDtoList);
}
