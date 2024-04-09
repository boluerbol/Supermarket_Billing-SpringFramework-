package com.supermarket.billing.mappers;

import com.supermarket.billing.dto.ClientDTO;
import com.supermarket.billing.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "companyName")
    @Mapping(target = "transactions", ignore = true)
    ClientDTO clientToClientDTO(Client client);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "companyName", target = "username")
    Client clientDTOToClient(ClientDTO clientDTO);
}
