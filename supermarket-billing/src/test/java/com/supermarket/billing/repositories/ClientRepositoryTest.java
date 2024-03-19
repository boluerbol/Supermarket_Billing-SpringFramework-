package com.supermarket.billing.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.supermarket.billing.entity.Client;

@DataJpaTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    // Test for saving a client to the database
    @Test
    public void testSaveClient() {
        // Create a new client
        Client client = new Client();
        client.setUsername("testclient");
        client.setEmail("testclient@example.com");

        // Save the client to the database
        clientRepository.save(client);

        // Retrieve the saved client from the database
        Client savedClient = clientRepository.findById(client.getId()).orElse(null);

        // Assert that the saved client is equal to the original client
        assertEquals(client, savedClient);
    }

    // Test for finding all clients in the database
    @Test
    public void testFindAllClients() {
        // Retrieve all clients from the database
        Iterable<Client> clients = clientRepository.findAll();

        // Assert that the list of clients is not null
        assertNotNull(clients);
        // Add more assertions to verify the list of clients if needed
    }

    @Test
    public void testDeleteClient() {
        // Create a new client
        Client client = new Client();
        client.setUsername("testclient");
        client.setEmail("testclient@example.com");

        // Save the client to the database
        clientRepository.save(client);

        // Delete the client from the database
        clientRepository.deleteById(client.getId());

        // Retrieve the deleted client from the database
        Client deletedClient = clientRepository.findById(client.getId()).orElse(null);

        // Assert that the deleted client is null, indicating it's no longer present in the database
        assertNull(deletedClient);
    }

    // Test for updating a client in the database
    @Test
    public void testUpdateClient() {
        // Create a new client
        Client client = new Client();
        client.setUsername("testclient");
        client.setEmail("testclient@example.com");

        // Save the client to the database
        clientRepository.save(client);

        // Update the client's username
        client.setUsername("updatedclient");

        // Save the updated client to the database
        clientRepository.save(client);

        // Retrieve the updated client from the database
        Client updatedClient = clientRepository.findById(client.getId()).orElse(null);

        // Assert that the updated client's username matches the new value
        assertEquals("updatedclient", updatedClient.getUsername());
    }

    // Add more test methods for other repository operations as needed
}
