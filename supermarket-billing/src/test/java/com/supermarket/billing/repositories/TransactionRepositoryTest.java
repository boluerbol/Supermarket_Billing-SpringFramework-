package com.supermarket.billing.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.supermarket.billing.entity.Transaction;
import com.supermarket.billing.repositories.TransactionRepository;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void testSaveTransaction() {
        Transaction transaction = new Transaction();
        // Set transaction details
        transactionRepository.save(transaction);

        Optional<Transaction> savedTransaction = transactionRepository.findById(transaction.getId());
        assertEquals(transaction, savedTransaction.orElse(null));
    }

    @Test
    public void testFindAllTransactions() {
        // Save some transactions
        Transaction transaction1 = new Transaction();
        // Set transaction1 details
        Transaction transaction2 = new Transaction();
        // Set transaction2 details
        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);

        List<Transaction> transactions = (List<Transaction>) transactionRepository.findAll();
        assertEquals(2, transactions.size());
    }

    @Test
    public void testUpdateTransaction() {
        // Save a transaction with initial details
        Transaction transaction = new Transaction();
        // Set initial transaction details
        transactionRepository.save(transaction);

        // Retrieve the saved transaction
        Optional<Transaction> retrievedTransaction = transactionRepository.findById(transaction.getId());
        assertTrue(retrievedTransaction.isPresent());

        // Modify the retrieved transaction
        Transaction modifiedTransaction = retrievedTransaction.get();
        modifiedTransaction.setAmount(100.00); // For example, update the amount
        transactionRepository.save(modifiedTransaction);

        // Retrieve the transaction again to verify the update
        Optional<Transaction> updatedTransaction = transactionRepository.findById(transaction.getId());
        assertTrue(updatedTransaction.isPresent());
        assertEquals(100.00, updatedTransaction.get().getAmount());
    }


    @Test
    public void testDeleteTransaction() {
        Transaction transaction = new Transaction();
        // Set transaction details
        transactionRepository.save(transaction);

        transactionRepository.delete(transaction);

        Optional<Transaction> deletedTransaction = transactionRepository.findById(transaction.getId());
        assertEquals(Optional.empty(), deletedTransaction);
    }
}
