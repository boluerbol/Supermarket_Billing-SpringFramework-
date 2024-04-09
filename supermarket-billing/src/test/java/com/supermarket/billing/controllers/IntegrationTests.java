package com.supermarket.billing.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.supermarket.billing.entity.Client;
import com.supermarket.billing.entity.Customer;
import com.supermarket.billing.entity.Product;
import com.supermarket.billing.entity.Transaction;
import com.supermarket.billing.services.ClientService;
import com.supermarket.billing.services.CustomerService;
import com.supermarket.billing.services.ProductService;
import com.supermarket.billing.services.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class IntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClientService clientService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private TransactionService transactionService;

    @Test
    public void testClientCRUD() throws Exception {
        Client client = new Client(/* Add necessary parameters */);

        // Create client
        mockMvc.perform(post("/api/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());

        // Retrieve client by ID
        mockMvc.perform(get("/api/clients/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        // Update client
        client.setUsername("Updated Name");
        mockMvc.perform(put("/api/clients/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Name"));

        // Delete client
        mockMvc.perform(delete("/api/clients/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testCustomerCRUD() throws Exception {
        Customer customer = new Customer(/* Add necessary parameters */);

        // Create customer
        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());

        // Retrieve customer by ID
        mockMvc.perform(get("/api/customers/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        // Update customer
        customer.setName("Updated Name");
        mockMvc.perform(put("/api/customers/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Name"));

        // Delete customer
        mockMvc.perform(delete("/api/customers/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testProductCRUD() throws Exception {
        Product product = new Product(/* Add necessary parameters */);

        // Create product
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());

        // Retrieve product by ID
        mockMvc.perform(get("/api/products/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        // Update product
        product.setName("Updated Product");
        mockMvc.perform(put("/api/products/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Product"));

        // Delete product
        mockMvc.perform(delete("/api/products/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testTransactionCRUD() throws Exception {
        Transaction transaction = new Transaction(/* Add necessary parameters */);

        // Create transaction
        mockMvc.perform(post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());

        // Retrieve transaction by ID
        mockMvc.perform(get("/api/transactions/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        // Update transaction
        mockMvc.perform(put("/api/transactions/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Updated Transaction"));

        // Delete transaction
        mockMvc.perform(delete("/api/transactions/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}

