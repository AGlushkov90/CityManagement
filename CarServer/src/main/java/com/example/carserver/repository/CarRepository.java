package com.example.carserver.repository;

import com.example.carserver.dto.CarDto;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.generated.tables.Car;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
@AllArgsConstructor
public class CarRepository {
    private final DSLContext dsl;

    public CarDto saveCar(CarDto carDto) {
        return dsl.insertInto(Car.CAR)
                .set(dsl.newRecord(Car.CAR, carDto))
                .returning()
                .fetchOptional()
                .orElseThrow(() -> new RuntimeException("Error inserting entity: " + carDto.getId()))
                .into(CarDto.class);
    }

    public Set<CarDto> getCars() {
        return new HashSet<>(dsl.selectFrom(Car.CAR)
                .fetchInto(CarDto.class));
    }

    public CarDto getCar(int id) {
        return dsl.selectFrom(Car.CAR)
                .where(Car.CAR.ID.eq(id))
                .fetchAny()
                .into(CarDto.class);
    }

    public boolean deleteCar(CarDto carDto) {
        return dsl.deleteFrom(Car.CAR)
                .where(Car.CAR.ID.eq(carDto.getId()))
                .execute() == 1;
    }


    public CarDto updateCar(int id, CarDto carDto) {
        return dsl.update(Car.CAR)
                .set(dsl.newRecord(Car.CAR, carDto))
                .where(Car.CAR.ID.eq(id))
                .returning()
                .fetchOptional()
                .orElseThrow(() -> new RuntimeException("Error inserting entity: " + carDto.getId()))
                .into(CarDto.class);
    }
}
