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

//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    public void testProcessPayment() throws Exception {
        // Prepare test data
        TransactionRequest request = new TransactionRequest();
        request.setTransactionId("123");
        request.setCardDetails(new CardDetails("1234567890123456", "12/25", "123", 100.0, "USD", "merchant123"));

        TransactionResponse expectedResponse = new TransactionResponse("123", "SUCCESS", "Payment processed successfully");

        // Mock the service method
        when(pspService.processPayment(any(TransactionRequest.class))).thenReturn(expectedResponse);

        // Perform the POST request to the controller
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/psp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.transactionId").value("123"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Payment processed successfully"));

        // Verify that the service method was called with the correct argument
        verify(pspService).processPayment(any(TransactionRequest.class));
    }
}
