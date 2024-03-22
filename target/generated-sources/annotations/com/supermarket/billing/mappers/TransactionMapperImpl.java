package com.supermarket.billing.mappers;

import com.supermarket.billing.dto.TransactionDTO;
import com.supermarket.billing.entity.Transaction;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-22T16:26:24+0600",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class TransactionMapperImpl implements TransactionMapper {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public TransactionDTO transactionToTransactionDTO(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }

        TransactionDTO.TransactionDTOBuilder transactionDTO = TransactionDTO.builder();

        transactionDTO.products( productMapper.productToProductDTO( transaction.getProduct() ) );
        transactionDTO.id( transaction.getId() );
        transactionDTO.customer( customerMapper.customerToCustomerDTO( transaction.getCustomer() ) );
        transactionDTO.amount( transaction.getAmount() );
        transactionDTO.description( transaction.getDescription() );

        return transactionDTO.build();
    }

    @Override
    public Transaction transactionDTOToTransaction(TransactionDTO transactionDTO) {
        if ( transactionDTO == null ) {
            return null;
        }

        Transaction.TransactionBuilder transaction = Transaction.builder();

        transaction.id( transactionDTO.getId() );
        transaction.customer( customerMapper.customerDTOToCustomer( transactionDTO.getCustomer() ) );
        transaction.amount( transactionDTO.getAmount() );
        transaction.description( transactionDTO.getDescription() );

        return transaction.build();
    }
}
