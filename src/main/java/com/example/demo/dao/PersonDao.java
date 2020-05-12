package com.example.demo.dao;

import java.util.Optional;
import java.util.UUID;
import java.util.List;
import com.example.demo.model.Person;

// defines operations allowed for the actual contract for anyone that wants to use this interface
// is cool because we can then use dependency injection to switch between implementations easily :)
public interface PersonDao {

    // WE CAN INSERT WITH AN ID
    int insertPerson(UUID id, Person person);

    default int insertPerson(Person person) {
        UUID id = UUID.randomUUID();    // WE CAN INSERT WITHOUT AN ID, RANDOMLY GENERATED :)
        return insertPerson(id, person);
    }

    List<Person> selectAllPeople();
    Optional<Person> selectPersonByID(UUID id);

    int deletePersonByID(UUID id);
    int updatePersonByID(UUID id, Person person);


}
