package com.divergentsl.psp.service;

import com.divergentsl.psp.domain.TransactionRequest;
import com.divergentsl.psp.domain.TransactionResponse;

public interface PspService {
	TransactionResponse processPayment(TransactionRequest transactionRequest);
}
