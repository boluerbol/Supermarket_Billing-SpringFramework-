package com.supermarket.billing.mappers;

import com.supermarket.billing.dto.TransactionDTO;
import com.supermarket.billing.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface TransactionMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "quantity", ignore = true)
    @Mapping(target = "Amount", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "description", ignore = true)
    TransactionDTO transactionToTransactionDTO(Transaction transaction);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "Amount", ignore = true)
    @Mapping(target = "description", ignore = true)
    Transaction transactionDTOToTransaction(TransactionDTO transactionDTO);
}

