package com.supermarket.billing.mappers;
import com.supermarket.billing.dto.TransactionDTO;
import com.supermarket.billing.entity.*;
import com.supermarket.billing.entity.Transaction;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionMapperTest{


    private final TransactionMapper mapper = Mappers.getMapper(TransactionMapper.class);

    @Test
    public void testTransactionToTransactionDTO() {
        // Create mock entities
        Client client = new Client();
        client.setId(1L);

        Customer customer = new Customer();
        customer.setId(2L);

        Product product = new Product();
        product.setId(3L);

        Transaction transaction = new Transaction();
        transaction.setId(100L);
        transaction.setClient(client);
        transaction.setCustomer(customer);
        transaction.setProduct(product);
        transaction.setQuantity(5);
        transaction.setTotalPrice(100.00);
        transaction.setTransactionTime(LocalDateTime.now());

        // Perform mapping
        TransactionDTO transactionDTO = mapper.transactionToTransactionDTO(transaction);

        // Verify mapping
        assertEquals(transaction.getId(), transactionDTO.getId());
        assertEquals(transaction.getClient().getId(), transactionDTO.getClientId());
        assertEquals(transaction.getCustomer().getId(), transactionDTO.getCustomerId());
        assertEquals(transaction.getProduct().getId(), transactionDTO.getProductId());
        assertEquals(transaction.getQuantity(), transactionDTO.getQuantity());
        assertEquals(transaction.getTotalPrice(), transactionDTO.getTotalPrice());
        assertEquals(transaction.getTransactionTime(), transactionDTO.getTransactionTime());
    }

    @Test
    public void testTransactionDTOToTransaction() {
        // Create mock DTO
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(100L);
        transactionDTO.setClientId(1L);
        transactionDTO.setCustomerId(2L);
        transactionDTO.setProductId(3L);
        transactionDTO.setQuantity(5);
        transactionDTO.setTotalPrice(100.00);
        transactionDTO.setTransactionTime(LocalDateTime.now());

        // Perform mapping
        Transaction transaction = mapper.transactionDTOToTransaction(transactionDTO);

        // Verify mapping
        assertEquals(transactionDTO.getId(), transaction.getId());
        assertEquals(transactionDTO.getClientId(), transaction.getClient().getId());
        assertEquals(transactionDTO.getCustomerId(), transaction.getCustomer().getId());
        assertEquals(transactionDTO.getProductId(), transaction.getProduct().getId());
        assertEquals(transactionDTO.getQuantity(), transaction.getQuantity());
        assertEquals(transactionDTO.getTotalPrice(), transaction.getTotalPrice());
        assertEquals(transactionDTO.getTransactionTime(), transaction.getTransactionTime());
    }
}
