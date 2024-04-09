package com.supermarket.billing.mappers;

import com.supermarket.billing.dto.ClientDTO;
import com.supermarket.billing.entity.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
public class ClientMapperTest {
    private final ClientMapper mapper = Mappers.getMapper(ClientMapper.class);

    @Test
    public void testClientToClientDTO() {
        Client client = new Client();
        client.setId(1L);
        client.setUsername("Test Company");
        ClientDTO clientDTO = mapper.clientToClientDTO(client);
        assertEquals(client.getId(), clientDTO.getId());
        assertEquals(client.getUsername(), clientDTO.getCompanyName());
    }

    @Test
    public void testClientDTOToClient() {
        // Given
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(1L);
        clientDTO.setCompanyName("Test Company");

        // When
        Client client = mapper.clientDTOToClient(clientDTO);

        // Then
        assertEquals(clientDTO.getId(), client.getId());
        assertEquals(clientDTO.getCompanyName(), client.getUsername());
    }
}
