package com.supermarket.billing.mappers;

import com.supermarket.billing.dto.CustomerDTO;
import com.supermarket.billing.dto.ProductDTO;
import com.supermarket.billing.dto.TransactionDTO;
import com.supermarket.billing.entity.Customer;
import com.supermarket.billing.entity.Product;
import com.supermarket.billing.entity.Transaction;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionMapperTest{


    private final TransactionMapper mapper = Mappers.getMapper(TransactionMapper.class);

    @Test
    void testTransactionToTransactionDTO() {
        // Create entities
        Product product = Product.builder()
                .id(1L)
                .name("Product 1")
                .price(10.0)
                .build();
        Customer customer = Customer.builder()
                .id(1L)
                .name("Customer 1")
                .email("customer1@example.com")
                .build();
        Transaction transaction = Transaction.builder()
                .id(1L)
                .product(product)
                .customer(customer)
                .amount(20.0)
                .description("Description")
                .build();

        // Map entity to DTO
        TransactionDTO transactionDTO = mapper.transactionToTransactionDTO(transaction);

        // Assertions
        assertEquals(transaction.getId(), transactionDTO.getId());
        assertEquals(transaction.getProduct().getId(), transactionDTO.getProducts().getId());
        assertEquals(transaction.getCustomer().getId(), transactionDTO.getCustomer().getId());
        assertEquals(transaction.getAmount(), transactionDTO.getAmount());
        assertEquals(transaction.getDescription(), transactionDTO.getDescription());
    }

    @Test
    void testTransactionDTOToTransaction() {
        // Create DTO
        ProductDTO productDTO = ProductDTO.builder()
                .id(1L)
                .name("Product 1")
                .price(10.0)
                .build();
        CustomerDTO customerDTO = CustomerDTO.builder()
                .id(1L)
                .name("Customer 1")
                .customerNumber("customer1@example.com")
                .build();
        TransactionDTO transactionDTO = TransactionDTO.builder()
                .id(1L)
                .products(productDTO)
                .customer(customerDTO)
                .amount(20.0)
                .description("Description")
                .build();

        // Map DTO to entity
        Transaction transaction = mapper.transactionDTOToTransaction(transactionDTO);

        // Assertions
        assertEquals(transactionDTO.getId(), transaction.getId());
        assertEquals(transactionDTO.getProducts().getId(), transaction.getProduct().getId());
        assertEquals(transactionDTO.getCustomer().getId(), transaction.getCustomer().getId());
        assertEquals(transactionDTO.getAmount(), transaction.getAmount());
        assertEquals(transactionDTO.getDescription(), transaction.getDescription());
    }

}
