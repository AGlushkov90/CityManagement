package com.example.citymanagement.dto;

import com.example.citymanagement.model.Passport;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PassportMapper {
    PassportDto ToPassportDto(Passport passport);
    Passport ToPassport(PassportDto passportDto);
}
