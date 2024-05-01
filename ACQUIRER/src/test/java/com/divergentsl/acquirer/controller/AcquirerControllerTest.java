package com.divergentsl.acquirer.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.divergentsl.acquirer.domain.TransactionRequest;
import com.divergentsl.acquirer.domain.TransactionResponse;
import com.divergentsl.acquirer.service.AcquirerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AcquirerController.class)
public class AcquirerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AcquirerService acquirerService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testProcessPayment() throws Exception {
		// Prepare test data
		TransactionRequest request = new TransactionRequest();
		request.setTransactionId("12345");
		// Prepare mocked service response
		TransactionResponse response = new TransactionResponse();
		response.setTransactionId("12345");
		response.setStatus("SUCCESS");
		response.setMessage("Payment processed successfully");
		// Mock the service method
		when(acquirerService.processPayment(any(TransactionRequest.class))).thenReturn(response);
		// Perform the POST request and verify the response
		mockMvc.perform(post("/api/v1/acquirer").contentType("application/json")
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isOk())
				.andExpect(jsonPath("$.transactionId").value("12345")).andExpect(jsonPath("$.status").value("SUCCESS"))
				.andExpect(jsonPath("$.message").value("Payment processed successfully"));
		// Verify that the service method was called with the correct argument
		verify(acquirerService, times(1)).processPayment(any(TransactionRequest.class));
	}
}
