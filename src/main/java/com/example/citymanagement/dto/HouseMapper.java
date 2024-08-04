package com.example.citymanagement.dto;

import com.example.citymanagement.model.House;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface HouseMapper {
    @Mapping(target = "persons", ignore = true)
    HouseDto toHouseDto(House house);
    House toHouse(HouseDto houseDto);

    Set<HouseDto> toHouseDtoList(Set<House> houseList);
    Set<House> toHouseList(Set<HouseDto> houseDtoList);
}
