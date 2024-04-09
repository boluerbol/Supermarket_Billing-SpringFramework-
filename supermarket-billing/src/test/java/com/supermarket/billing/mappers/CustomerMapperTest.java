package com.supermarket.billing.mappers;

import com.supermarket.billing.dto.CustomerDTO;
import com.supermarket.billing.entity.Customer;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CustomerMapperTest {

    private final CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    @Test
    void testCustomerToCustomerDTO() {
        Customer customer = Customer.builder()
                .id(1L)
                .name("Test Customer")
                .address("123 Main St")
                .email("test@example.com")
                .build();

        // Map Customer entity to CustomerDTO
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
        System.out.println(customerDTO.getAddress());
        System.out.println(customerDTO.getName());

        // Assertions
        assertNotNull(customerDTO);
        assertEquals(1L, customerDTO.getId());
        assertEquals("Test Customer", customerDTO.getName());
        assertEquals("123 Main St", customerDTO.getAddress());
        assertEquals("test@example.com", customerDTO.getEmail()); // Assuming contactNumber is mapped to contactNumber in DTO
    }

    @Test
    void testCustomerDTOToCustomer() {
        // Create a CustomerDTO
        CustomerDTO customerDTO = CustomerDTO.builder()
                .id(1L)
                .name("Test Customer DTO")
                .address("456 Oak St")
                .email("555-1234")
                .build();

        // Map CustomerDTO to Customer entity
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        System.out.println(customer.getAddress());

        // Assertions
        assertNotNull(customer);
        assertEquals(1L, customer.getId());
        assertEquals("Test Customer DTO", customer.getName());
        assertEquals("456 Oak St", customer.getAddress());
        assertEquals("555-1234", customer.getEmail()); // Assuming email is ignored or mapped to email in the entity
    }
}
