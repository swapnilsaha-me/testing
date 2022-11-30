package saha.swapnil.testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;
import saha.swapnil.testing.controller.PersonController;
import saha.swapnil.testing.model.Person;
import saha.swapnil.testing.repository.PersonRepository;
import saha.swapnil.testing.service.PersonService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

//@ExtendWith(MockitoExtension.class)
//@WebFluxTest(controllers = PersonController.class)
//@Import(PersonService.class)

/*@RunWith(SpringRunner.class)
@WebFluxTest(controllers = PersonController.class)
@SpringBootTest(classes = {
        PersonService.class
})*/
@SpringBootTest
//@WebFluxTest/*(controllers = PersonController.class)*/
@AutoConfigureMockMvc
public class EndpointTest {
    @MockBean
    //@Autowired
    PersonRepository personRepository;

    @Autowired
    private WebTestClient webTestClient;


    @Test
    public void test_createPerson() {

        Person person = new Person("Person_1", 10);
        Mockito.when(personRepository.save(person)).thenReturn(person);

        webTestClient.post()
                .uri("/createPerson")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(person))
                .exchange()
                .expectStatus().isOk();

        Mockito.verify(personRepository, times(1)).save(person);
    }
}
