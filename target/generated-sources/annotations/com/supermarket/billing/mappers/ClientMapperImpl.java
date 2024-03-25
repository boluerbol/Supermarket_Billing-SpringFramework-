package com.supermarket.billing.mappers;

import com.supermarket.billing.dto.ClientDTO;
import com.supermarket.billing.entity.Client;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-23T22:56:55+0600",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public ClientDTO clientToClientDTO(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientDTO.ClientDTOBuilder clientDTO = ClientDTO.builder();

        clientDTO.companyName( client.getUsername() );
        clientDTO.contactPerson( client.getEmail() );
        clientDTO.id( client.getId() );

        return clientDTO.build();
    }

    @Override
    public Client clientDTOToClient(ClientDTO clientDTO) {
        if ( clientDTO == null ) {
            return null;
        }

        Client client = new Client();

        client.setUsername( clientDTO.getCompanyName() );
        client.setEmail( clientDTO.getContactPerson() );
        client.setId( clientDTO.getId() );

        return client;
    }
}
