package com.divergentsl.psp.service;

import java.util.concurrent.ConcurrentSkipListMap;

import com.divergentsl.psp.domain.TransactionRequest;

public interface TransactionRepo {

	TransactionRequest findByTransactionId(String transactionId);

	void save(TransactionRequest transactionRequest);

	ConcurrentSkipListMap<String, TransactionRequest> findAll();

}
