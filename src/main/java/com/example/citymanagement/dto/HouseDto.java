package com.example.citymanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.Set;

@Setter
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "persons")
@ToString(exclude = "persons")
public class HouseDto {
    private int id;
    private String address;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<PersonDto> persons;
}
