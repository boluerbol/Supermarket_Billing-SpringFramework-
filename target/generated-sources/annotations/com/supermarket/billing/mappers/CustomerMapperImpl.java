package com.supermarket.billing.mappers;

import com.supermarket.billing.dto.CustomerDTO;
import com.supermarket.billing.entity.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-23T22:56:55+0600",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDTO customerToCustomerDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO.CustomerDTOBuilder customerDTO = CustomerDTO.builder();

        customerDTO.customerAddress( customer.getAddress() );
        customerDTO.id( customer.getId() );
        customerDTO.name( customer.getName() );

        return customerDTO.build();
    }

    @Override
    public Customer customerDTOToCustomer(CustomerDTO customerDTO) {
        if ( customerDTO == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.address( customerDTO.getCustomerAddress() );
        customer.email( customerDTO.getCustomerNumber() );
        customer.id( customerDTO.getId() );
        customer.name( customerDTO.getName() );

        return customer.build();
    }
}
