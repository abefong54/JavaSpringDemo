package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

// this makes it obvious that this class is acting as a repository
// this name allows us to have multiple implementations,
// example we could name this mongodb for mongoDB implementation
@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {

    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonByID(UUID id) {
        //search db for id given, use java stream to stream database: look into java streams
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePersonByID(UUID id) {
        Optional<Person> personMaybe = selectPersonByID(id);
        if(personMaybe.isEmpty()) {
            return 0;
        }
        DB.remove(personMaybe.get());
        return 1;
    }

    @Override
    public int updatePersonByID(UUID id, Person updatePerson) {

        // LOOK INTO JAVA STREAMS TO LEARN ABOUT MAPS, FILTER, ETC
        return selectPersonByID(id)
                .map(foundPerson -> {
                    int indexOfPersonToUpdate = DB.indexOf(foundPerson); // MAKE SURE TO USE THE INDEX OF THE PERSON FOUND IN THE LAST LINE
                    if(indexOfPersonToUpdate >= 0) {
                        DB.set(indexOfPersonToUpdate, new Person(id, updatePerson.getName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

}
