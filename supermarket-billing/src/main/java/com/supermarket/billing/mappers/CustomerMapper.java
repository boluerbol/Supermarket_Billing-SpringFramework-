package com.supermarket.billing.mappers;

import com.supermarket.billing.dto.CustomerDTO;

import com.supermarket.billing.entity.Customer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "email", ignore = true)
    CustomerDTO customerToCustomerDTO(Customer customer);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "contactNumber", ignore = true)
    Customer customerDTOToCustomer(CustomerDTO customerDTO);

}

