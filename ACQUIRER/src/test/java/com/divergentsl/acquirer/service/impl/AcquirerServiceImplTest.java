package com.divergentsl.acquirer.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.MessageSource;

import com.divergentsl.acquirer.constants.TransactionStatus;
import com.divergentsl.acquirer.domain.CardDetails;
import com.divergentsl.acquirer.domain.TransactionRequest;
import com.divergentsl.acquirer.domain.TransactionResponse;
import com.divergentsl.acquirer.util.TransactionUtil;

class AcquirerServiceImplTest {

    @Mock
    private MessageSource messageSource;

    @InjectMocks
    private AcquirerServiceImpl acquirerService;

    @SuppressWarnings("deprecation")
	@BeforeEach
    void setUp() {
    	
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testProcessPayment_Approved() {
        // Mock transaction request
        TransactionRequest transactionRequest = new TransactionRequest();
        CardDetails cardDetails = new CardDetails("378734493671000", "12/23", "123", 100.0, "USD", "merchant123");
        transactionRequest.setCardDetails(cardDetails);

        
        // Mock behavior of TransactionUtil
        when(TransactionUtil.isCardValidated("378734493671000")).thenReturn(true);

        // Mock message source behavior
        when(messageSource.getMessage("error.approved.transaction", new Object[] {}, Locale.getDefault()))
                .thenReturn("Transaction approved");

        // Invoke the method under test
        TransactionResponse transactionResponse = acquirerService.processPayment(transactionRequest);

        // Verify the response
        assertEquals(TransactionStatus.APPROVED.name(), transactionResponse.getStatus());
        assertEquals("Transaction approved", transactionResponse.getMessage());
    }

    @Test
    void testProcessPayment_Denied() {
        // Mock transaction request
        TransactionRequest transactionRequest = new TransactionRequest();
        CardDetails cardDetails = new CardDetails("378282246310005", "12/23", "123", 100.0, "USD", "merchant123");
        transactionRequest.setCardDetails(cardDetails);

        mockStatic(TransactionUtil.class);  //only single time
        // Mock behavior of TransactionUtil
        when(TransactionUtil.isCardValidated("378282246310005")).thenReturn(false);

        // Mock message source behavior
        when(messageSource.getMessage("error.invalid.card.details", new Object[] {}, Locale.getDefault()))
                .thenReturn("Invalid card details");

        // Invoke the method under test
        TransactionResponse transactionResponse = acquirerService.processPayment(transactionRequest);

        // Verify the response
        assertEquals(TransactionStatus.DENIED.name(), transactionResponse.getStatus());
    }
}
