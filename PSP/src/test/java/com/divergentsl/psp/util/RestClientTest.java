package com.divergentsl.psp.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.divergentsl.psp.domain.CardDetails;
import com.divergentsl.psp.domain.TransactionRequest;
import com.divergentsl.psp.domain.TransactionResponse;
import com.divergentsl.psp.exception.GenericException;

@SpringBootTest
public class RestClientTest {

	@Autowired
	private RestClient restClient;

	@MockBean
	private RestTemplate restTemplate;

	@Test
	public void testProcessTransaction_Failed() {
		// Prepare test data
		TransactionRequest request = new TransactionRequest();
		request.setTransactionId("123");
		request.setCardDetails(new CardDetails("1234567890123456", "12/25", "123", 100.0, "USD", "merchant123"));
		HttpStatus errorStatus = HttpStatus.INTERNAL_SERVER_ERROR;

		mockRestTemplateExchange(errorStatus);

		// Call the method under test and expect an exception
		try {
			restClient.processTransaction(request);
		} catch (GenericException ex) {
			// Verify that the exception message contains the expected error status
			assertEquals("error.failed.transaction", ex.getMessage());
			assertEquals(errorStatus, ex.getArgs()[0]);
		}
	}

	@SuppressWarnings("unchecked")
	private void mockRestTemplateExchange(HttpStatus errorStatus) {
		// Mock the restTemplate.exchange method to return a failed response
        when(restTemplate.exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class)))
            .thenReturn(new ResponseEntity<>(errorStatus));
	}

	@Test
	public void testProcessTransaction_SUCCESS() {
		// Prepare test data
		TransactionRequest request = new TransactionRequest();
		request.setTransactionId("123");
		request.setCardDetails(new CardDetails("1234567890123456", "12/25", "123", 100.0, "USD", "merchant123"));
		HttpStatus success = HttpStatus.OK;

		mockRestTemplateExchange(success);

		// Call the method under test
		TransactionResponse actualResponse = restClient.processTransaction(request);

		// Verify that the response matches the expected response
		assertEquals(null, actualResponse);
	}
}
