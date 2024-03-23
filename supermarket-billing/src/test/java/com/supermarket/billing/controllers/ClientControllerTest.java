package com.supermarket.billing.controllers;

import com.supermarket.billing.entity.Client;
import com.supermarket.billing.services.ClientService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Test
    void getAllClients() throws Exception {
        when(clientService.getAllClients()).thenReturn(Collections.singletonList(new Client()));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/clients")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1));
    }

    @Test
    void getClientById() throws Exception {
        Client client = new Client();
        client.setId(1L);

        when(clientService.getClientById(1L)).thenReturn(client);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/clients/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    void createClient() throws Exception {
        Client client = new Client();
        client.setId(1L);

        when(clientService.createClient(any(Client.class))).thenReturn(client);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/clients")
                        .content("{ \"username\": \"test\", \"email\": \"test@example.com\" }")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    void updateClient() throws Exception {
        Client updatedClient = new Client();
        updatedClient.setId(1L);
        updatedClient.setUsername("updated");
        updatedClient.setEmail("updated@example.com");

        when(clientService.updateClient(eq(1L), any(Client.class))).thenReturn(updatedClient);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/clients/1")
                        .content("{ \"username\": \"updated\", \"email\": \"updated@example.com\" }")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1)); // Ensure the 'id' field is present
    }

    @Test
    void deleteClient() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/clients/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
