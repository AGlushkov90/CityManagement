package com.example.citymanagementnew.service;

import com.example.citymanagementnew.config.MyUserDetails;
import com.example.citymanagementnew.dto.jwt.JwtAuthenticationDto;
import com.example.citymanagementnew.dto.jwt.UserCredentialsDto;
import com.example.citymanagementnew.exception.EntityNotCreatedException;
import com.example.citymanagementnew.exception.EntityNotDeletedException;
import com.example.citymanagementnew.exception.EntityNotFoundException;
import com.example.citymanagementnew.exception.EntityNotUpdatedException;
import com.example.citymanagementnew.kafka.KafkaTestProducer;
import com.example.citymanagementnew.model.House;
import com.example.citymanagementnew.model.MyUser;
import com.example.citymanagementnew.model.Passport;
import com.example.citymanagementnew.model.Person;
import com.example.citymanagementnew.repository.PersonRepository;
import com.example.citymanagementnew.repository.UserRepository;
import com.example.citymanagementnew.security.jwt.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.naming.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import com.example.citymanagementnew.dto.jwt.RefreshTokenDto;

import java.util.*;

@Service
@AllArgsConstructor
public class PersonServiceImpl {

    private final PersonRepository personRepository;
    private final HouseServiceImpl  houseService;
    private final PassportServiceImpl passportService;
    private final TransactionTemplate transactionTemplate;
    private KafkaTestProducer kafkaTestProducer;
    private UserRepository repository;
    private PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    //    @Transactional()
    public Person savePerson(Person person) {

        try {
            return transactionTemplate.execute(status -> {
                Set<House> houseSet = new HashSet<>();
                if (person.getHouses() != null)
                {
                    findHouse(houseSet, person);
                    person.setHouses(houseSet);
                }
                Passport passport = passportService.savePassport();
                person.setPassport(passport);
                return personRepository.save(person);
            });
        } catch (Exception e) {
            throw new EntityNotCreatedException("Person not created");
        }
    }

    @Transactional
    public Person updatePerson(Long id, Person person) {
        try {
        Optional<Person> personData = personRepository.findById(id);
        if (personData.isPresent()) {
            Person currentPerson = personData.get();
            currentPerson.setName(person.getName());
            currentPerson.setBankAccountBalance(person.getBankAccountBalance());
            currentPerson.setDeleted(person.isDeleted());
            Set<House> houseSet = new HashSet<>();
            findHouse(houseSet, person);
            currentPerson.setHouses(houseSet);
            return personRepository.save(currentPerson);
        } else {
            throw new EntityNotFoundException("Person not found", id);
        }
        } catch (Exception e) {
            throw new EntityNotUpdatedException("Person not updated", id);
        }
    }

    public Set<Person> findAllPersons() {
        return new HashSet<>(personRepository.findAll());
    }

    public Set<Person> findByStreet(String street){
        return new HashSet<>(personRepository.findByStreet(street));
    }

    public Set<Passport> findAllPassportsByPersonName(String letter){
        return new HashSet<>(passportService.findAllPassportsByPersonName(letter));
    }

    public Person findPersonById(Long id) {
        Optional<Person> personData = personRepository.findById(id);
        return personData.
                orElseThrow(() -> new EntityNotFoundException("Person not found ", id));
    }

     @Transactional
    public ResponseEntity<HttpStatus> deletePerson(Person person) {
        long personId = person.getId();
        try {
            person.setDeleted(true);
            this.updatePerson(personId, person);
            kafkaTestProducer.sendMessage("deleteCar", String.valueOf(personId));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new EntityNotDeletedException("Person not deleted", personId);
        }
    }

    public ResponseEntity<HttpStatus> cancelDeletePerson(int personId) {
        try {
            Person person = findPersonById((long) personId);
            person.setDeleted(false);
            this.updatePerson((long) personId, person);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void findHouse(Set<House> houseSet, Person person) {
        if (person.getHouses() == null) {
            person.setHouses(new HashSet<>());
        }
        for (House h : person.getHouses()) {
            House houseData = houseService.findByAddress(h.getAddress());
            houseSet.add(Objects.requireNonNullElse(houseData, h));
        }
    }

    public void addUser(MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }


    public JwtAuthenticationDto singIn(UserCredentialsDto userCredentialsDto) throws Exception {
        MyUser user = findByCredentials(userCredentialsDto);
        return jwtService.generateAuthToken(user.getName());
    }


    public JwtAuthenticationDto refreshToken(RefreshTokenDto refreshTokenDto) throws Exception {
        String refreshToken = refreshTokenDto.getRefreshToken();
        if (refreshToken != null && jwtService.validateJwtToken(refreshToken)) {
            MyUser user = findByName(jwtService.getNameFromToken(refreshToken));
            return jwtService.refreshBaseToken(user.getName(), refreshToken);
        }
        throw new  AuthenticationException("Invalid refresh token");
    }

    private MyUser findByCredentials(UserCredentialsDto userCredentialsDto) throws Exception {
        MyUser user = findByName(userCredentialsDto.getName());
            if (passwordEncoder.matches(userCredentialsDto.getPassword(), user.getPassword())){
                return user;
        }
        throw new AuthenticationException("Nsme or password is not correct");
    }

    private MyUser findByName(String name) throws Exception {
        return repository.findByName(name).orElseThrow(()->
                new Exception(String.format("User with name %s not found", name)));
    }
}
