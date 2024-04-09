package com.supermarket.billing.mappers;

import com.supermarket.billing.dto.TransactionDTO;
import com.supermarket.billing.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "product.id", target = "productId")
    TransactionDTO transactionToTransactionDTO(Transaction transaction);

    @Mapping(source = "clientId", target = "client.id")
    @Mapping(source = "customerId", target = "customer.id")
    @Mapping(source = "productId", target = "product.id")
    Transaction transactionDTOToTransaction(TransactionDTO transactionDTO);
}