package com.supermarket.billing.controllers;

import com.supermarket.billing.entity.Customer;
import com.supermarket.billing.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    public void testGetAllCustomers() throws Exception {
        List<Customer> customers = Arrays.asList(
                new Customer(1L, "John", "123 Main St", "john@example.com", null),
                new Customer(2L, "Alice", "456 Elm St", "alice@example.com", null)
        );
        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(customers.size()));
    }

    @Test
    public void testGetCustomerById() throws Exception {
        Customer customer = new Customer(1L, "John", "123 Main St", "john@example.com", null);
        when(customerService.getCustomerById(1L)).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(customer.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(customer.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value(customer.getAddress()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(customer.getEmail()));
    }

    @Test
    public void testCreateCustomer() throws Exception {
        Customer customer = new Customer(1L, "John", "123 Main St", "john@example.com", null);
        when(customerService.createCustomer(any(Customer.class))).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John\",\"address\":\"123 Main St\",\"email\":\"john@example.com\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(customer.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(customer.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value(customer.getAddress()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(customer.getEmail()));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        // Create a sample updated customer object
        Customer updatedCustomer = new Customer(null, "John", "123 Main St", "john@example.com", null);

        // Mock the service method to return the updated customer object
        when(customerService.updateCustomer(1L, updatedCustomer)).thenReturn(updatedCustomer);

        // Perform the PUT request to update the customer
        mockMvc.perform(MockMvcRequestBuilders.put("/api/customers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John\",\"address\":\"123 Main St\",\"email\":\"john@example.com\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                // Assert other fields as needed
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(updatedCustomer.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value(updatedCustomer.getAddress()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(updatedCustomer.getEmail()));
    }



    @Test
    public void testDeleteCustomer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/customers/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
