package com.supermarket.billing.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.supermarket.billing.entity.Customer;
import java.util.List;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testSaveCustomer() {
        Customer customer = new Customer();
        customer.setName("Test Customer");
        customer.setEmail("testcustomer@example.com");
        customerRepository.save(customer);

        Customer savedCustomer = customerRepository.findById(customer.getId()).orElse(null);
        assertEquals(customer, savedCustomer);
    }

    @Test
    public void testUpdateCustomer() {
        // Save a customer
        Customer customer = new Customer();
        customer.setName("Test Customer");
        customer.setEmail("testcustomer@example.com");
        customerRepository.save(customer);

        // Update the customer
        customer.setName("Updated Customer");
        customerRepository.save(customer);

        // Retrieve the updated customer from the database
        Customer updatedCustomer = customerRepository.findById(customer.getId()).orElse(null);
        assert updatedCustomer != null;
        assertEquals("Updated Customer", updatedCustomer.getName());
    }

    @Test
    public void testDeleteCustomer() {

        Customer customer = new Customer();
        customer.setName("Test Customer");
        customer.setEmail("testcustomer@example.com");
        customerRepository.save(customer);

        // Delete the customer
        customerRepository.deleteById(customer.getId());

        // Ensure the customer is no longer in the database
        assertFalse(customerRepository.existsById(customer.getId()));
    }

    @Test
    public void testFindAllCustomer() {
        // Save some customers
        Customer customer1 = new Customer();
        customer1.setName("Customer 1");
        customer1.setEmail("customer1@example.com");
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setName("Customer 2");
        customer2.setEmail("customer2@example.com");
        customerRepository.save(customer2);

        // Retrieve all customers from the repository
        List<Customer> customers = (List<Customer>) customerRepository.findAll();

        // Assert that the number of retrieved customers matches the expected count
        assertEquals(2, customers.size());
    }
}
