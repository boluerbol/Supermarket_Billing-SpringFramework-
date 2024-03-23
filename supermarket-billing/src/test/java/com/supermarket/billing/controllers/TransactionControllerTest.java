package com.supermarket.billing.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.supermarket.billing.controllers.*;
import com.supermarket.billing.entity.Transaction;
import com.supermarket.billing.services.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @Test
    public void testGetAllTransactions() throws Exception {
        // Prepare test data
        List<Transaction> transactions = new ArrayList<>();
        // Add some transactions to the list

        // Define the behavior of the transaction service
        when(transactionService.getAllTransactions()).thenReturn(transactions);

        // Set up MockMvc
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();

        // Perform GET request
        mockMvc.perform(get("/api/transactions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        // Verify that the transaction service's getAllTransactions method was called
        verify(transactionService).getAllTransactions();
    }

    @Test
    public void testGetTransactionById() throws Exception {
        // Prepare test data
        Long id = 1L;
        Transaction transaction = new Transaction(/* Add necessary parameters */);

        // Define the behavior of the transaction service
        when(transactionService.getTransactionById(id)).thenReturn(transaction);

        // Set up MockMvc
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();

        // Perform GET request
        mockMvc.perform(get("/api/transactions/{id}", id))
                .andExpect(status().isOk());
        // Add assertions for other fields as needed

        // Verify that the transaction service's getTransactionById method was called
        verify(transactionService).getTransactionById(id);
    }

//    @Test
//    public void testCreateTransaction() throws Exception {
//        // Create a mock transaction
//        Transaction transaction = new Transaction(/* Add necessary parameters */);
//
//        // Define the behavior of the transaction service
//        when(transactionService.createTransaction(transaction)).thenReturn(transaction);
//
//        // Set up MockMvc
//        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
//
//        // Convert the transaction to JSON
//        String jsonBody = new ObjectMapper().writeValueAsString(transaction);
//
//        // Perform the POST request
//        mockMvc.perform(post("/api/transactions")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonBody))
//                .andExpect(status().isCreated());
//
//        // Verify that the transaction service's createTransaction method was called
//        verify(transactionService).createTransaction(transaction);
//    }

//    @Test
//    public void testUpdateTransaction() throws Exception {
//        // Prepare test data
//        Long id = 1L;
//        Transaction transaction = new Transaction(/* Add necessary parameters */);
//
//        // Define the behavior of the transaction service
//        when(transactionService.updateTransaction(id, transaction)).thenReturn(transaction);
//
//        // Set up MockMvc
//        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
//
//        // Convert the transaction to JSON
//        String jsonBody = new ObjectMapper().writeValueAsString(transaction);
//
//        // Perform the PUT request
//        mockMvc.perform(put("/api/transactions/{id}", id)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonBody))
//                .andExpect(status().isOk());
//
//        // Verify that the transaction service's updateTransaction method was called
//        verify(transactionService).updateTransaction(id, transaction);
//    }

    @Test
    public void testDeleteTransaction() throws Exception {
        // Prepare test data
        Long id = 1L;

        // Set up MockMvc
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();

        // Perform the DELETE request
        mockMvc.perform(delete("/api/transactions/{id}", id))
                .andExpect(status().isNoContent());

        // Verify that the transaction service's deleteTransaction method was called
        verify(transactionService).deleteTransaction(id);
    }
}
