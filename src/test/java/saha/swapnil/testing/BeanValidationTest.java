package saha.swapnil.testing;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import saha.swapnil.testing.model.Person;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BeanValidationTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeClass
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterClass
    public static void close() {
        validatorFactory.close();
    }

    @Test
    public void noViolationCheck() {
        Person person = new Person("Person_1", 10);
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void nameValidationError() {
        Person person = new Person("A", 10);
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        assertEquals(violations.size(), 1);

        ConstraintViolation<Person> violation = violations.iterator().next();
        assertEquals("size must be between 2 and 10", violation.getMessage());
        assertEquals("name", violation.getPropertyPath().toString());
        assertEquals("A", violation.getInvalidValue());
    }

    @Test
    public void ageMaxValidationCheck() {
        Person person = new Person("Person_1", 60);
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        assertEquals(violations.size(), 1);

        ConstraintViolation<Person> violation = violations.iterator().next();
        assertEquals("must be less than or equal to 50", violation.getMessage());
        assertEquals("age", violation.getPropertyPath().toString());
        assertEquals(60, violation.getInvalidValue());
    }

    @Test
    public void ageMinValidationCheck() {
        Person person = new Person("Person_1", 0);
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        assertEquals(violations.size(), 1);

        ConstraintViolation<Person> violation = violations.iterator().next();

        // TODO
        /*
         * Remove the underscores and put proper message, and values
         */
//        assertEquals(_, violation.getMessage());
//        assertEquals(_, violation.getPropertyPath().toString());
//        assertEquals(_, violation.getInvalidValue());
    }
}
