package com.example.carserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class CarDto {
    private int id;
    private String model;
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int person_id;


}
