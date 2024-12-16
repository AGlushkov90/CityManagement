package com.example.citymanagementnew.dto;

import com.example.citymanagementnew.model.Passport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PassportMapper {
    @Mapping(target = "person", ignore = true)
    PassportDto ToPassportDto(Passport passport);
    Passport ToPassport(PassportDto passportDto);

    @Mapping(target = "person", ignore = true)
    Set<PassportDto> toPassportDtoList(Set<Passport> passportList);
    Set<Passport> toPassportList(Set<PassportDto> passportDtoList);
}
