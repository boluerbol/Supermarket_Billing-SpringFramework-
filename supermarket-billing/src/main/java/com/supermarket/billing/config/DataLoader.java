package com.supermarket.billing.config;

import com.supermarket.billing.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import com.supermarket.billing.repositories.ClientRepository; // Import your ClientRepository

@Component
@Profile("!test") // Exclude this loader in the test profile
public class DataLoader implements CommandLineRunner {

    private final ClientRepository clientRepository;

    @Autowired
    public DataLoader(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadSampleData();
    }

    private void loadSampleData() {
        // Create sample users
        Client client1 = new Client();
        client1.setUsername("user1");
        client1.setEmail("user1@example.com");

        Client client2 = new Client();
        client2.setUsername("user2");
        client2.setEmail("user2@example.com");

        // Save users to the database
        clientRepository.save(client1);
        clientRepository.save(client2);

        // You can add more sample data for other entities if needed
    }
}
