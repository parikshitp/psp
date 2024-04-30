package com.divergentsl.psp.service.impl;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.divergentsl.psp.constants.TransactionStatus;
import com.divergentsl.psp.domain.CardDetails;
import com.divergentsl.psp.domain.TransactionRequest;
import com.divergentsl.psp.domain.TransactionResponse;
import com.divergentsl.psp.exception.EntityNotFoundException;
import com.divergentsl.psp.service.PspService;
import com.divergentsl.psp.service.TransactionRepo;
import com.divergentsl.psp.util.RestClient;
import com.divergentsl.psp.util.TransactionUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PspServiceImpl implements PspService {

	private final RestClient restClient;
	private final MessageSource messageSource;
	private final TransactionRepo transactionRepo;

	public PspServiceImpl(RestClient restClient, MessageSource messageSource, TransactionRepo transactionRepo) {
		this.restClient = restClient;
		this.messageSource = messageSource;
		this.transactionRepo = transactionRepo;
	}

	/**
	 * to process the payment
	 * 
	 * @param transactionRequest = it is used to take transaction details
	 * @return TransactionResponse = it contains message and status
	 */
	@Override
	public TransactionResponse processPayment(TransactionRequest transactionRequest) {
		log.info("Inside Psp paymentService method processPayment: {}", transactionRequest);

		TransactionResponse transactionResponse = new TransactionResponse();

		// setting transaction status
		transactionRequest.setTransactionStatus(TransactionStatus.PENDING.name());

		CardDetails cardDetails = transactionRequest.getCardDetails();

		// generating transaction id
		String transactionId = TransactionUtil.generateTransactionId(cardDetails.getMerchantId(),
				cardDetails.getCardNumber());

		transactionRequest.setTransactionId(transactionId);
		transactionResponse.setTransactionId(transactionId);
		
		boolean isValidCardNumber = TransactionUtil.isValidCardLuhansAlgorithm(cardDetails.getCardNumber());

		boolean approved = TransactionUtil.isApproved(cardDetails.getCardNumber());
		if (!approved || !isValidCardNumber) {
			transactionResponse.setStatus(TransactionStatus.DENIED.name());
			transactionResponse.setMessage(
					messageSource.getMessage("error.invalid.card.details", new Object[] {}, Locale.getDefault()));
			log.info("Inside Psp paymentService method processPayment: {}", transactionResponse);
			return transactionResponse;
		}

		transactionResponse = updateTransactionRecord(transactionRequest, transactionId);

		log.info("Inside Psp paymentService method processPayment: {}", transactionResponse);

		return transactionResponse;
	}

	/**
	 * it is used to update the transaction record in the memory
	 * 
	 * @param transactionRequest
	 * @param transactionId
	 * @return
	 */
	private TransactionResponse updateTransactionRecord(TransactionRequest transactionRequest, String transactionId) {
		TransactionResponse transactionResponse;
		transactionRepo.save(transactionRequest);

		transactionResponse = restClient.processTransaction(transactionRequest);
		transactionResponse.setTransactionId(transactionId);

		TransactionRequest savedTransactionRequest = transactionRepo.findByTransactionId(transactionId);

		if (savedTransactionRequest == null) {
			throw new EntityNotFoundException("error.entity.not.exist", new Object[] { transactionId });
		}

		savedTransactionRequest.setTransactionStatus(transactionResponse.getStatus());
		transactionRepo.save(savedTransactionRequest);
		return transactionResponse;
	}
}
