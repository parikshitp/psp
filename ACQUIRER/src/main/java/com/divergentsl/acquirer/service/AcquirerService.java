package com.divergentsl.acquirer.service;

import com.divergentsl.acquirer.domain.TransactionRequest;
import com.divergentsl.acquirer.domain.TransactionResponse;

public interface AcquirerService {
	TransactionResponse processPayment(TransactionRequest transactionRequest);
}
