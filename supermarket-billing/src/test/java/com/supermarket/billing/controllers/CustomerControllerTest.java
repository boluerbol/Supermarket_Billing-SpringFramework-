package com.supermarket.billing.controllers;

import com.supermarket.billing.entity.Customer;
import com.supermarket.billing.services.CustomerService;
import org.hibernate.validator.constraints.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    public void testGetAllCustomers() throws Exception {
        List<Customer> customers = Arrays.asList(
                new Customer(1L, "John", "123 Main St", "john@example.com", null),
                new Customer(2L, "Alice", "456 Elm St", "alice@example.com", null)
        );
        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(customers.size()));
    }

    @Test
    public void testGetCustomerById() throws Exception {
        Customer customer = new Customer(1L, "John", "123 Main St", "john@example.com", null);
        when(customerService.getCustomerById(any())).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/1"))
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
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customers")
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
        Customer updatedCustomer = new Customer(1L, "John", "123 Main St", "john@example.com", null);

        when(customerService.updateCustomer(any(), any(Customer.class))).thenReturn(updatedCustomer);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/customers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John\",\"address\":\"123 Main St\",\"email\":\"john@example.com\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(updatedCustomer.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value(updatedCustomer.getAddress()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(updatedCustomer.getEmail()));
    }


    @Test
    public void testDeleteCustomer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/customers/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
