package com.supermarket.billing.mappers;

import com.supermarket.billing.dto.CustomerDTO;
import com.supermarket.billing.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(target = "transactions" , ignore = true)
    CustomerDTO customerToCustomerDTO(Customer customer);
    @Mapping(target = "transactions" , ignore = true)
    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}
