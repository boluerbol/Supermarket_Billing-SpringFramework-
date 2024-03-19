package com.supermarket.billing.mappers;

import com.supermarket.billing.dto.ClientDTO;
import com.supermarket.billing.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ClientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "email", ignore = true)
    ClientDTO clientToClientDTO(Client client);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "companyName", ignore = true)
    @Mapping(target = "contactPerson", ignore = true)
    Client clientDTOToClient(ClientDTO clientDTO);
}
