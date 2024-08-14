package com.example.carserver.dto;


import org.jooq.generated.tables.Car;
import org.jooq.generated.tables.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
    public interface CarMapper {
        CarDto toCarDto(Car car);
        Car toCar(CarDto carDto);

        Set<CarDto> toCarDtoSet(Set<Car> carSet);
//        Set<Car> toCarSet(Set<CarDto> carDtoSet);
//
//    PersonDto toPersonDto(Person person);
//    Person toPerson(PersonDto personDto);
//
//    Set<PersonDto> toPersonDtoList(Set<Person> personList);
//    Set<Person> toPersonList(Set<PersonDto> personDtoList);
    }

