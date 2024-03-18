package com.supermarket.billing.repositories;

import com.supermarket.billing.entity.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void testSaveClient() {
        // Create a new client entity
        Client client = new Client();
        client.setUsername("testUser");
        client.setEmail("test@example.com");

        // Save the client entity
        Client savedClient = clientRepository.save(client);

        // Verify that the client is saved successfully
        assertThat(savedClient.getId()).isNotNull();
        assertThat(savedClient.getUsername()).isEqualTo("testUser");
        assertThat(savedClient.getEmail()).isEqualTo("test@example.com");
    }

    // Add more test methods as needed
}