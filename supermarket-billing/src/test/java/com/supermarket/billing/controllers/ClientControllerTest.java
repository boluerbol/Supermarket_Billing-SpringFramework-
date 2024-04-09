package com.supermarket.billing.controllers;

import com.supermarket.billing.entity.Client;
import com.supermarket.billing.services.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @MockBean
    ClientService clientService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getAllClients() throws Exception {
        when(clientService.getAllClients()).thenReturn(Collections.singletonList(new Client()));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/clients")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    void getClientById() throws Exception {
        Client client = new Client();
        client.setId(1L);

        when(clientService.getClientById(1L)).thenReturn(client);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/clients/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void createClient() throws Exception {
        Client client = new Client();
        client.setId(1L);

        when(clientService.createClient(any(Client.class))).thenReturn(client);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/clients")
                        .content("{ \"username\": \"test\", \"email\": \"test@example.com\" }")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void updateClient() throws Exception {
        Client updatedClient = new Client();
        updatedClient.setId(1L);
        updatedClient.setUsername("updated");
        updatedClient.setEmail("updated@example.com");
        when(clientService.updateClient(any(), any(Client.class))).thenReturn(updatedClient);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/clients/1")
                        .content("{ \"username\": \"updated\", \"email\": \"updated@example.com\" }")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(1L)); // Ensure the 'id' field is present
    }

    @Test
    void deleteClient() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/clients/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}