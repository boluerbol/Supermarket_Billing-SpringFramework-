package com.supermarket.billing.mappers;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import com.supermarket.billing.entity.Transaction;
import com.supermarket.billing.dto.TransactionDTO;

@Component
public class TransactionMapperImpl implements TransactionMapper {

    private final ProductMapper productMapper;
    private final CustomerMapper customerMapper;
    // Default constructor
    public TransactionMapperImpl() {
        this.productMapper = Mappers.getMapper(ProductMapper.class);
        this.customerMapper = Mappers.getMapper(CustomerMapper.class);
    }


    // Constructor injection
    public TransactionMapperImpl(ProductMapper productMapper, CustomerMapper customerMapper) {
        this.productMapper = productMapper;
        this.customerMapper = customerMapper;
    }

    @Override
    public TransactionDTO transactionToTransactionDTO(Transaction transaction) {
        if (transaction == null) {
            return null;
        }

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(transaction.getId());
        transactionDTO.setProducts(productMapper.productToProductDTO(transaction.getProduct()));
        transactionDTO.setCustomer(customerMapper.customerToCustomerDTO(transaction.getCustomer()));
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setDescription(transaction.getDescription());
        return transactionDTO;
    }

    @Override
    public Transaction transactionDTOToTransaction(TransactionDTO transactionDTO) {
        if (transactionDTO == null) {
            return null;
        }

        Transaction transaction = new Transaction();
        transaction.setId(transactionDTO.getId());
        transaction.setProduct(productMapper.productDTOToProduct(transactionDTO.getProducts()));
        transaction.setCustomer(customerMapper.customerDTOToCustomer(transactionDTO.getCustomer()));
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setDescription(transactionDTO.getDescription());
        return transaction;
    }

    // Other mapping methods here
}
