package com.supermarket.billing.mappers;

import com.supermarket.billing.dto.ClientDTO;
import com.supermarket.billing.entity.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClientMapperTest {
    @Autowired
    ClientMapper clientMapper;
    @Test
    void testToDTO() {
        Client client = new Client();
        client.setId(1L);
        client.setUsername("Test Client");
        client.setEmail("test@example.com");
        // Set other properties

        ClientDTO clientDTO = clientMapper.clientToClientDTO(client);

        assertNotNull(clientDTO);
        assertEquals(1L, clientDTO.getId());
        assertEquals("Test Client", clientDTO.getCompanyName());
        assertEquals("test@example.com", clientDTO.getContactPerson());
        // Assert other properties
    }

    @Test
    public void testToDTO_NullInput() {
        ClientDTO clientDTO = clientMapper.clientToClientDTO(null);

        assertNull(clientDTO);
        // Assert default values or null checks for DTO properties
    }

    @Test
    void testToEntity() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(1L);
        clientDTO.setCompanyName("Test Client DTO");
        clientDTO.setContactPerson("testdto@example.com");
        // Set other properties

        Client client = clientMapper.clientDTOToClient(clientDTO);

        assertNotNull(client);
        assertEquals(1L, client.getId());
        assertEquals("Test Client DTO", client.getUsername());
        assertEquals("testdto@example.com", client.getEmail());
        // Assert other properties
    }

    @Test
    void testToEntity_NullInput() {
        Client client = clientMapper.clientDTOToClient(null);

        assertNull(client);
        // Assert default values or null checks for entity properties
    }
}
