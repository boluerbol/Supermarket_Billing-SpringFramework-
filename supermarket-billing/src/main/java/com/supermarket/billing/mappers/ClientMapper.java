package com.supermarket.billing.mappers;

import com.supermarket.billing.dto.ClientDTO;
import com.supermarket.billing.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface ClientMapper {

    @Mappings({
            @Mapping(target = "companyName", source = "username"),
            @Mapping(target = "contactPerson", source = "email"),
            // Map other fields if needed
    })
    ClientDTO clientToClientDTO(Client client);

    @Mappings({
            @Mapping(target = "username", source = "companyName"),
            @Mapping(target = "email", source = "contactPerson"),
            // Map other fields if needed
    })
    Client clientDTOToClient(ClientDTO clientDTO);
}
