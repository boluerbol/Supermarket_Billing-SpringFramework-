package com.supermarket.billing.exceptionHandler;

import com.supermarket.billing.entity.Client;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserValidationTest {

    private final LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();

    @Test
    public void testClientValidation() {
        validator.afterPropertiesSet();
        Client client = new Client(); // Assuming User class with validation annotations
        client.setUsername(""); // Invalid, as it's blank

        Set<ConstraintViolation<Client>> violations = validator.validate(client);
        assertEquals(1, violations.size());
        assertEquals("Username is required", violations.iterator().next().getMessage());
    }
}
