package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonDao personDao;


    @Autowired // this allows us to inject a persondao via the param
    public PersonService(@Qualifier("fakeDao") PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person) {
        return personDao.insertPerson(person);
    }

    public List<Person> getAllPeople() {
        return personDao.selectAllPeople();
    }

    public Optional<Person> getPersonByID(UUID id) {
        return personDao.selectPersonByID(id);
    }

    public int deletePerson(UUID id) {
        return personDao.deletePersonByID(id);
    }

    public int updatePerson(UUID id, Person person) {
        return personDao.updatePersonByID(id, person);
    }

 }
