package com.divergentsl.psp.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.divergentsl.psp.domain.CardDetails;
import com.divergentsl.psp.domain.TransactionRequest;
import com.divergentsl.psp.domain.TransactionResponse;
import com.divergentsl.psp.service.PspService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PspController.class)
public class PspControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PspService pspService;

	@Test
	public void testProcessPayment() throws Exception {
		// Prepare test data
		TransactionRequest request = new TransactionRequest();
		request.setCardDetails(new CardDetails("378734493671000", "12/25", "123", 100.0, "USD", "merchant123"));

		TransactionResponse expectedResponse = new TransactionResponse("657d5d68-82ba-499f-9023-3389b62a426f",
				"APPROVED", "Transaction Approved");

		// Mock the service method
		when(pspService.processPayment(any(TransactionRequest.class))).thenReturn(expectedResponse);

		// Perform the POST request to the controller
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/process_payment").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(request)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(
						MockMvcResultMatchers.jsonPath("$.transactionId").value("657d5d68-82ba-499f-9023-3389b62a426f"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("APPROVED"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Transaction Approved"));

		// Verify that the service method was called with the correct argument
		verify(pspService).processPayment(any(TransactionRequest.class));
	}
}
