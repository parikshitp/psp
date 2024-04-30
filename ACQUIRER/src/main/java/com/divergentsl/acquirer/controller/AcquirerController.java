package com.divergentsl.acquirer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.divergentsl.acquirer.constants.UriConstants;
import com.divergentsl.acquirer.domain.TransactionRequest;
import com.divergentsl.acquirer.domain.TransactionResponse;
import com.divergentsl.acquirer.service.AcquirerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(UriConstants.API + UriConstants.V1 + UriConstants.ACQUIRER)
public class AcquirerController {

	private final AcquirerService acquirerService;

	public AcquirerController(AcquirerService acquirerService) {
		this.acquirerService = acquirerService;
	}

	/**
	 * to process the payment
	 * 
	 * @param transactionRequest = it is used to take transaction details
	 * @return TransactionResponse = it contains message and status
	 */
	@PostMapping
	public TransactionResponse processPayment(@RequestBody TransactionRequest transactionRequest) {
		log.info("Inside AcquirerController method processPayment: {}", transactionRequest);
		TransactionResponse transactionRespone = acquirerService.processPayment(transactionRequest);
		log.info("Inside AcquirerController method processPayment: {}", transactionRespone);
		return transactionRespone;
	}
}
