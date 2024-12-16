package com.example.carserver.dto;


import org.jooq.generated.tables.Car;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
    public interface CarMapper {
        CarDto toCarDto(Car car);
        Car toCar(CarDto carDto);
        Set<CarDto> toCarDtoSet(Set<Car> carSet);
    }

