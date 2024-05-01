package com.divergentsl.psp.repo.impl;

import java.util.concurrent.ConcurrentSkipListMap;

import org.springframework.stereotype.Repository;

import com.divergentsl.psp.domain.TransactionRequest;
import com.divergentsl.psp.repo.TransactionRepo;

@Repository
public class TransactionRepoImpl implements TransactionRepo {

	private static ConcurrentSkipListMap<String, TransactionRequest> transactionMap = new ConcurrentSkipListMap<>();

	/**
	 * it is used to find transaction with transactionId
	 * 
	 * @param transactionId = it is transaction id
	 * @return TransactionRequest = it is used to return transaction record
	 */
	@Override
	public TransactionRequest findByTransactionId(String transactionId) {
		return transactionMap.get(transactionId);
	}

	/**
	 * it is used to save transaction record
	 * 
	 * @param transactionRequest it contains transaction details
	 */
	@Override
	public void save(TransactionRequest transactionRequest) {
		transactionMap.put(transactionRequest.getTransactionId(), transactionRequest);
	}

	/**
	 * it is used to get all transaction with transactionId
	 * 
	 * @return ConcurrentSkipListMap<String,TransactionRequest> = it is used to
	 *         return all transaction record
	 */
	@Override
	public ConcurrentSkipListMap<String, TransactionRequest> findAll() {
		return transactionMap;
	}
}
