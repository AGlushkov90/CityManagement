package com.example.carserver.dto;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.RecordUnmapper;
import org.jooq.exception.MappingException;
import org.jooq.generated.tables.Car;
import org.jooq.generated.tables.records.CarRecord;
import org.springframework.stereotype.Component;

import static org.jooq.generated.tables.Car.CAR;


@Component
@RequiredArgsConstructor
public class CarRecordUnmapper implements RecordUnmapper<Car, CarRecord> {

    private final DSLContext dsl;

    @Override
    public CarRecord unmap(Car car) throws MappingException {
        CarRecord record = dsl.newRecord(Car.CAR, car);
        return record;
    }
}