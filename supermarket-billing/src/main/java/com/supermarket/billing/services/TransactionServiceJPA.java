package com.supermarket.billing.services;

import com.supermarket.billing.entity.Client;
import com.supermarket.billing.entity.Transaction;
import com.supermarket.billing.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceJPA implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getAllTransactions() {
        return (List<Transaction>) transactionRepository.findAll();
    }

    @Override
    public Transaction getTransactionById(Long id) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(id);
        return transactionOptional.orElse(null);
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction updateTransaction(Long id, Transaction updatedTransaction) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        if (optionalTransaction.isPresent()) {
            updatedTransaction.setId(id);
            updatedTransaction.setClient(optionalTransaction.get().getClient());
            updatedTransaction.setCustomer(optionalTransaction.get().getCustomer());
            updatedTransaction.setTotalPrice(optionalTransaction.get().getTotalPrice());
            return transactionRepository.save(updatedTransaction);
        }
        return null;
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}
