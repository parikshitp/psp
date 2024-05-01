package com.divergentsl.psp.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.divergentsl.psp.domain.TransactionRequest;
import com.divergentsl.psp.domain.TransactionResponse;
import com.divergentsl.psp.exception.GenericException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RestClient {

	private final RestTemplate restTemplate;

	@Value("${acquirer.base.url}")
	private String acquirerBaseUrl;

	public RestClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	/**
	 * it is used call the Acquirer service
	 * 
	 * @param transactionRequest
	 * @return TransactionResponse
	 */
	public TransactionResponse processTransaction(TransactionRequest transactionRequest) {
		log.info("Inside Psp RestTemplate method processTransaction: {}", transactionRequest);

		String acquirerUrl = acquirerBaseUrl;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<TransactionRequest> requestEntity = new HttpEntity<>(transactionRequest, headers);
		ResponseEntity<TransactionResponse> responseEntity = restTemplate.exchange(acquirerUrl, HttpMethod.POST,
				requestEntity, TransactionResponse.class);

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			TransactionResponse transactionResponse = responseEntity.getBody();

			log.info("Inside Psp RestTemplate method processTransaction: {}", transactionResponse);

			return transactionResponse;
		} else {
			throw new GenericException("error.failed.transaction", new Object[] { responseEntity.getStatusCode() });
		}
	}

}
