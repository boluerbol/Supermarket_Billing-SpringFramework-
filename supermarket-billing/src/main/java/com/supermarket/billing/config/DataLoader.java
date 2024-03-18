package com.supermarket.billing.config;

import com.supermarket.billing.entity.Client;
import com.supermarket.billing.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import com.supermarket.billing.repositories.*; // Import your ClientRepository
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
@Component
@Profile("!test")
public class DataLoader implements CommandLineRunner {
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public DataLoader(ClientRepository clientRepository, ProductRepository productRepository,
                      CustomerRepository customerRepository, TransactionRepository transactionRepository) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadSampleData();
    }

    private void loadSampleData() {
        // Generate and save clients
        for (int i = 1; i <= 100; i++) {
            Client client = new Client();
            client.setUsername("user" + i);
            client.setEmail("user" + i + "@example.com");
            clientRepository.save(client);
        }

        // Generate and save products
        for (int i = 1; i <= 100; i++) {
            Product product = new Product();
            product.setName("Product" + i);
            product.setPrice(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(1, 100)));
            productRepository.save(product);
        }

        // Generate and save customers
        for (int i = 1; i <= 100; i++) {
            Customer customer = new Customer();
            customer.setName("Customer" + i);
            customer.setEmail("customer" + i + "@example.com");
            customerRepository.save(customer);
        }

        // Generate and save transactions
        List<Client> clients = (List<Client>) clientRepository.findAll();
        List<Product> products = (List<Product>) productRepository.findAll();
        List<Customer> customers = (List<Customer>) customerRepository.findAll();

        for (int i = 0; i < 100; i++) {
            Transaction transaction = new Transaction();
            transaction.setClient(clients.get(ThreadLocalRandom.current().nextInt(clients.size())));
            transaction.setProduct(products.get(ThreadLocalRandom.current().nextInt(products.size())));
            transaction.setCustomer(customers.get(ThreadLocalRandom.current().nextInt(customers.size())));
            transaction.setQuantity(ThreadLocalRandom.current().nextInt(1, 10));
            transactionRepository.save(transaction);
        }
    }
}
