package com.supermarket.billing.mappers;

import com.supermarket.billing.dto.TransactionDTO;
import com.supermarket.billing.entity.Transaction;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-19T15:18:45+0600",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
public class TransactionMapperImpl implements TransactionMapper {

    @Override
    public TransactionDTO transactionToTransactionDTO(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }

        TransactionDTO transactionDTO = new TransactionDTO();

        return transactionDTO;
    }

    @Override
    public List<TransactionDTO> transactionsToTransactionDTOs(List<Transaction> transactions) {
        if ( transactions == null ) {
            return null;
        }

        List<TransactionDTO> list = new ArrayList<TransactionDTO>( transactions.size() );
        for ( Transaction transaction : transactions ) {
            list.add( transactionToTransactionDTO( transaction ) );
        }

        return list;
    }

    @Override
    public Transaction transactionDTOToTransaction(TransactionDTO transactionDTO) {
        if ( transactionDTO == null ) {
            return null;
        }

        Transaction transaction = new Transaction();

        return transaction;
    }

    @Override
    public List<Transaction> transactionDTOsToTransactions(List<TransactionDTO> transactionDTOs) {
        if ( transactionDTOs == null ) {
            return null;
        }

        List<Transaction> list = new ArrayList<Transaction>( transactionDTOs.size() );
        for ( TransactionDTO transactionDTO : transactionDTOs ) {
            list.add( transactionDTOToTransaction( transactionDTO ) );
        }

        return list;
    }
}
