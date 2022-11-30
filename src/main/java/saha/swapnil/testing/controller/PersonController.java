package saha.swapnil.testing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saha.swapnil.testing.model.Person;
import saha.swapnil.testing.service.PersonService;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/createPerson")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        return new ResponseEntity<>(personService.createPerson(person), HttpStatus.OK);
    }

    @GetMapping("/getAllPerson")
    public ResponseEntity<List<Person>> getAllPerson() {
        return new ResponseEntity<>(personService.getAllPerson(), HttpStatus.OK);
    }

    @GetMapping("/getPersonByName/{name}")
    public ResponseEntity<List<Person>> getPersonByName(@PathVariable String name) {
        return new ResponseEntity<>(personService.getPersonByName(name), HttpStatus.OK);
    }

    @GetMapping("/getPersonByAge")
    public ResponseEntity<List<Person>> getPersonByAge(@RequestParam(value = "age") Integer age) {
        return new ResponseEntity<>(personService.getPersonByAge(age), HttpStatus.OK);
    }

    @DeleteMapping("/deletePerson")
    public void deletePerson(@RequestBody Person person) {
        personService.deletePerson(person);
    }
}
