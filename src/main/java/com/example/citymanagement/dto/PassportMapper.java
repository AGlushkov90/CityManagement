package com.example.citymanagement.dto;

import com.example.citymanagement.model.Passport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PassportMapper {
    @Mapping(target = "person", ignore = true)
    PassportDto ToPassportDto(Passport passport);
    Passport ToPassport(PassportDto passportDto);

    @Mapping(target = "person", ignore = true)
    Set<PassportDto> toPassportDtoList(Set<Passport> passportList);
    Set<Passport> toPassportList(Set<PassportDto> passportDtoList);
}
