package com.supermarket.billing.mappers;

import com.supermarket.billing.dto.TransactionDTO;
import com.supermarket.billing.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, CustomerMapper.class})
public interface TransactionMapper {

    @Mapping(target = "products", source = "product")
    TransactionDTO transactionToTransactionDTO(Transaction transaction);

    Transaction transactionDTOToTransaction(TransactionDTO transactionDTO);
}
