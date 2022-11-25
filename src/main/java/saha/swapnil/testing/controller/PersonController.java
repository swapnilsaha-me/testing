package saha.swapnil.testing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import saha.swapnil.testing.model.Person;
import saha.swapnil.testing.service.PersonService;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/createPerson")
    public Person createPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }

    @GetMapping("/getAllPerson")
    public List<Person> getAllPerson() {
        return personService.getAllPerson();
    }

    @GetMapping("getPersonByName/{name}")
    public List<Person> getPersonByName(@PathVariable String name) {
        return personService.getPersonByName(name);
    }

    @DeleteMapping("/deletePerson")
    public void deletePerson(@RequestBody Person person) {
        personService.deletePerson(person);
    }
}
