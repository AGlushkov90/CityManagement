package com.example.FeignClient.client;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class CarDto {
    private int id;
    private String model;
    private int person_id;
}
