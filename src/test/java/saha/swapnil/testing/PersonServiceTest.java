package saha.swapnil.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import saha.swapnil.testing.model.Person;
import saha.swapnil.testing.repository.PersonRepository;
import saha.swapnil.testing.service.PersonService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @MockBean
    private PersonRepository personRepository;

    @Test
    public void createPerson_PassTest() {
        Person person = new Person("Alice", 10);
        when(personRepository.save(person)).thenReturn(person);
        assertEquals(person, personService.createPerson(person));
    }

    @Test
    public void createPerson_FailTest() {
        Person person = new Person("Alice", 10);
        when(personRepository.save(person)).thenReturn(person);

        Person person1 = new Person("Alice", 15);
        assertEquals(person1, personService.createPerson(person));
    }

    private List<Person> getDummyPersonList(int n) {
        List<Person> personList = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            Person person = new Person("Person_" + i, i + 10);
            personList.add(person);
        }
        return personList;
    }

    @Test
    public void getAllPerson_PassTest() {
        int total = 5;
        when(personRepository.findAll()).thenReturn(getDummyPersonList(total));
        assertEquals(total, personService.getAllPerson().size());
    }

    @Test
    public void getAllPerson_FailTest() {
        int total = 5;
        when(personRepository.findAll()).thenReturn(getDummyPersonList(total));
        assertEquals(total + 1, personService.getAllPerson().size());
    }

    @Test
    public void getPersonByName_PassTest() {
        int total = 5;
        String name = "Person";
        when(personRepository.findByName(name)).thenReturn(getDummyPersonList(total));
        assertEquals(total, personService.getPersonByName(name).size());
    }

    @Test
    public void getPersonByName_FailTest() {
        int total = 5;
        String name = "Person";
        when(personRepository.findByName(name)).thenReturn(getDummyPersonList(total));
        assertEquals(total + 2, personService.getPersonByName(name).size());
    }

    @Test
    public void deletePerson_PassTest() {
        Person person = new Person("Person", 10);
        personService.deletePerson(person);
        verify(personRepository, times(1)).delete(person);
    }

    @Test
    public void deletePerson_FailTest() {
        Person person = new Person("Person", 10);
        personService.deletePerson(person);
        verify(personRepository, times(2)).delete(person);
    }
}
