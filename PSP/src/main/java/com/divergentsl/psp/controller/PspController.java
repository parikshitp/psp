package com.divergentsl.psp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.divergentsl.psp.constants.UriConstants;
import com.divergentsl.psp.domain.TransactionRequest;
import com.divergentsl.psp.domain.TransactionResponse;
import com.divergentsl.psp.service.PspService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(UriConstants.API + UriConstants.V1)
public class PspController {

	private final PspService pspService;

	public PspController(PspService pspService) {
		this.pspService = pspService;
	}

	/**
	 * to process the payment
	 * 
	 * @param transactionRequest = it is used to take transaction details
	 * @return TransactionResponse = it contains message and status
	 */
	@PostMapping(UriConstants.PROCESS_PAYMENT)
	public TransactionResponse processPayment(@RequestBody @Valid TransactionRequest transactionRequest) {
		log.info("Inside Psp controller method processPayment: {}", transactionRequest);

		TransactionResponse transactionResponse = pspService.processPayment(transactionRequest);

		log.info("Inside Psp controller method processPayment: {}", transactionResponse);

		return transactionResponse;
	}

}
