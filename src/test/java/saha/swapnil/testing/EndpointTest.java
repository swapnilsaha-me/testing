package saha.swapnil.testing;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import saha.swapnil.testing.model.Person;
import saha.swapnil.testing.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class EndpointTest {
    @MockBean
    PersonRepository personRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void test_createPerson() {
        Person person = new Person("Person_1", 10);
        Mockito.when(personRepository.save(person)).thenReturn(person);
        webTestClient.post().uri("/createPerson")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(person))
                .exchange()
                .expectStatus()
                .isOk();
        Mockito.verify(personRepository, times(1)).save(person);
    }

    private List<Person> getDummyPersonList(int n) {
        List<Person> personList = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            Person person = new Person("Person", 10);
            personList.add(person);
        }
        return personList;
    }

    @Test
    public void test_getAllPerson() {
        int total = 5;
        Mockito.when(personRepository.findAll()).thenReturn(getDummyPersonList(total));
        webTestClient.get().uri("/getAllPerson")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.size()").isEqualTo(total)
                .jsonPath("$[0].name").isEqualTo("Person")
                .jsonPath("$[0].age").isEqualTo(10);

        Mockito.verify(personRepository, times(1)).findAll();
        assertEquals(total, personRepository.findAll().size());
    }

    @Test
    public void test_getPersonByName() {
        int total = 10;
        when(personRepository.findByName("Person")).thenReturn(getDummyPersonList(total));
        webTestClient.get().uri("/getPersonByName/Person")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.size()").isEqualTo(total);
    }

    // TODO
    /*
     * Put the correct url to pass the test case
     */
//    @Test
//    public void test_getPersonByAge() {
//        int total = 10;
//        when(personRepository.findByAge(10)).thenReturn(getDummyPersonList(total));
//        webTestClient.get().uri("")
//                .exchange()
//                .expectStatus()
//                .isOk()
//                .expectBody()
//                .jsonPath("$.size()").isEqualTo(total);
//    }

    @Test
    public void test_deletePerson() {
        Person person = new Person("Person", 10);
        webTestClient.method(HttpMethod.DELETE)
                .uri("/deletePerson")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(person))
                .exchange()
                .expectStatus()
                .isOk();
    }
}
