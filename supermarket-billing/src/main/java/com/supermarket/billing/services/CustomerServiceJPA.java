package com.supermarket.billing.services;

import com.supermarket.billing.entity.Customer;
import com.supermarket.billing.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceJPA implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceJPA(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        return customerOptional.orElse(null);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Optional<Customer> existingCustomerOptional = customerRepository.findById(id);
        if (existingCustomerOptional.isPresent()) {
            Customer existingCustomer = existingCustomerOptional.get();
            // Update the existing customer's details
            existingCustomer.setName(customer.getName());
            existingCustomer.setEmail(customer.getEmail());
            // Update other properties as needed

            // Save the updated customer
            return customerRepository.save(existingCustomer);
        } else {
            throw new IllegalArgumentException("Customer with ID " + id + " not found");
        }
    }
    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
