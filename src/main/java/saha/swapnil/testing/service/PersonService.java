package saha.swapnil.testing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saha.swapnil.testing.model.Person;
import saha.swapnil.testing.repository.PersonRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public List<Person> getAllPerson() {
        return personRepository.findAll();
    }

    public List<Person> getPersonByName(String name) {
        return personRepository.findByName(name);
    }

    public void deletePerson(Person person) {
        personRepository.delete(person);
    }
}
