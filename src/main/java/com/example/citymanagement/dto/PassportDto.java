package com.example.citymanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(exclude="person")
@ToString(exclude="person")
public class PassportDto {
    private int id;
    private String number;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private PersonDto person;
}
