package com.example.demo.api;
import java.net.IDN;
import java.util.List;
import java.util.UUID;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@Valid @NonNull @RequestBody Person person) {
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }

    @GetMapping(path="{id}") // define the name of the variable in the path
    public Person getPersonByID(@PathVariable("id") UUID id) { // PathVariable helps you get variables passed through the URL
        return personService.getPersonByID(id).orElse(null);
    }

    @DeleteMapping(path="{id}")
    public void deletePersonByID(@PathVariable("id") UUID id) {
         personService.deletePerson(id);
    }

    @PutMapping(path="{id}")
    public void updatePersonByID(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Person updatePerson) {
        personService.updatePerson(id, updatePerson);
    }


}
