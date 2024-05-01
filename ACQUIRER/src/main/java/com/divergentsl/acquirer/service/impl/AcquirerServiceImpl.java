package com.divergentsl.acquirer.service.impl;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.divergentsl.acquirer.constants.TransactionStatus;
import com.divergentsl.acquirer.domain.CardDetails;
import com.divergentsl.acquirer.domain.TransactionRequest;
import com.divergentsl.acquirer.domain.TransactionResponse;
import com.divergentsl.acquirer.service.AcquirerService;
import com.divergentsl.acquirer.util.TransactionUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AcquirerServiceImpl implements AcquirerService {

	private MessageSource messageSource;

	private AcquirerServiceImpl(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * to process the payment
	 * 
	 * @param transactionRequest = it is used to take transaction details
	 * @return TransactionResponse = it contains message and status
	 */
	@Override
	public TransactionResponse processPayment(TransactionRequest transactionRequest) {
		log.info("Inside AcquirerService method processPayment: {}", transactionRequest);

		TransactionResponse transactionResponse = new TransactionResponse();

		CardDetails cardDetails = transactionRequest.getCardDetails();

		// Simulate interaction with mock acquirer
		boolean approved = TransactionUtil.isCardValidated(cardDetails.getCardNumber());

		transactionResponse.setTransactionId(transactionRequest.getTransactionId());
		transactionResponse.setStatus(approved ? TransactionStatus.APPROVED.name() : TransactionStatus.DENIED.name());
		transactionResponse.setMessage(
				approved ? messageSource.getMessage("error.approved.transaction", new Object[] {}, Locale.getDefault())
						: messageSource.getMessage("error.denied.transaction", new Object[] {}, Locale.getDefault()));

		log.info("Inside AcquirerService method processPayment: {}", transactionResponse);
		return transactionResponse;
	}

}
