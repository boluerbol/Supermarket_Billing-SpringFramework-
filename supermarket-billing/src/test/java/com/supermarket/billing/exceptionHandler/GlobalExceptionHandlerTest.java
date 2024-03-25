package com.supermarket.billing.exceptionHandler;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    public void testHandleValidationException() {
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, null);
        ResponseEntity<String> responseEntity = globalExceptionHandler.handleValidationException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Validation failed", responseEntity.getBody()); // Assuming this message is returned for simplicity
    }
}
