package saha.swapnil.testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saha.swapnil.testing.model.Person;

import java.util.List;

//@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByName(String name);

    List<Person> findByAge(Integer age);
}
