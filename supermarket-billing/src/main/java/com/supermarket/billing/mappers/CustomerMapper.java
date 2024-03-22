package com.supermarket.billing.mappers;

import com.supermarket.billing.dto.CustomerDTO;
import com.supermarket.billing.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(source = "address", target = "customerAddress") // Example of field mapping
    CustomerDTO customerToCustomerDTO(Customer customer);

    @Mapping(source = "customerAddress", target = "address")
    @Mapping(source = "customerNumber", target = "email")// Example of ignoring a field
    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}
