package com.divergentsl.repo.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.concurrent.ConcurrentSkipListMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.divergentsl.psp.domain.TransactionRequest;
import com.divergentsl.psp.repo.impl.TransactionRepoImpl;

public class TransactionRepoImplTest {

	private TransactionRepoImpl transactionRepo;

	@BeforeEach
	public void setUp() {
		transactionRepo = new TransactionRepoImpl();
	}

	@Test
	public void testSaveAndFindByTransactionId() {
		// Create a transaction request
		TransactionRequest transactionRequest = new TransactionRequest();
		transactionRequest.setTransactionId("123");
		transactionRequest.setTransactionStatus("SUCCESS");

		// Save the transaction request
		transactionRepo.save(transactionRequest);

		// Retrieve the saved transaction request
		TransactionRequest retrievedTransaction = transactionRepo.findByTransactionId("123");

		// Assert that the retrieved transaction matches the saved one
		assertEquals(transactionRequest, retrievedTransaction);
	}

	@Test
	public void testFindByTransactionIdNotFound() {
		// Retrieve a non-existent transaction
		TransactionRequest retrievedTransaction = transactionRepo.findByTransactionId("non_existent_id");

		// Assert that no transaction is retrieved
		assertNull(retrievedTransaction);
	}

	@Test
	public void testFindAll() {
		// Create some transaction requests
		TransactionRequest transaction1 = new TransactionRequest();
		transaction1.setTransactionId("123");
		transaction1.setTransactionStatus("SUCCESS");

		TransactionRequest transaction2 = new TransactionRequest();
		transaction2.setTransactionId("456");
		transaction2.setTransactionStatus("PENDING");

		// Save the transaction requests
		transactionRepo.save(transaction1);
		transactionRepo.save(transaction2);

		// Retrieve all transactions
		ConcurrentSkipListMap<String, TransactionRequest> allTransactions = transactionRepo.findAll();

		// Assert that the size of the map is 2 and contains both transactions
		assertEquals(2, allTransactions.size());
		assertEquals(transaction1, allTransactions.get("123"));
		assertEquals(transaction2, allTransactions.get("456"));
	}
}
