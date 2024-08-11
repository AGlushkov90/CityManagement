package com.example.citymanagement.service;

import com.example.citymanagement.exception.EntityNotCreatedException;
import com.example.citymanagement.model.Passport;
import com.example.citymanagement.repository.PassportRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class PassportServiceImpl {

    private final PassportRepository passportRepository;

    public Passport savePassport() {
        try {
            Passport passport = new Passport();
            passport.setNumber(generateNumber());
            return passportRepository.save(passport);
        } catch (Exception e) {
            throw new EntityNotCreatedException("Passport not created from person");
        }
    }

    public Set<Passport> findAllPassportsByPersonName(String letter){
        return new HashSet<>(passportRepository.findAllPassportsByPersonName(letter));
    }

    private String generateNumber() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(1 + (int) (Math.random() * 9));
        }
        return sb.toString();
    }
}
