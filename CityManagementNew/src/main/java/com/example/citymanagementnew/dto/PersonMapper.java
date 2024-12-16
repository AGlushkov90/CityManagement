package com.example.citymanagementnew.dto;

import com.example.citymanagementnew.model.Passport;
import com.example.citymanagementnew.model.Person;
import com.example.citymanagementnew.model.House;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
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
