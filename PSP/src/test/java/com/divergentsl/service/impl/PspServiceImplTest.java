package com.divergentsl.service.impl;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;

import com.divergentsl.psp.domain.CardDetails;
import com.divergentsl.psp.domain.TransactionRequest;
import com.divergentsl.psp.domain.TransactionResponse;
import com.divergentsl.psp.repo.TransactionRepo;
import com.divergentsl.psp.service.impl.PspServiceImpl;
import com.divergentsl.psp.util.RestClient;

public class PspServiceImplTest {

//    @InjectMocks
    private PspServiceImpl pspService;

//    @Mock
    private RestClient restClient;

//    @Mock
    private MessageSource messageSource;

//    @Mock
    private TransactionRepo transactionRepo;


    @BeforeEach
    public void setUp() {
        restClient = mock(RestClient.class);
        messageSource = mock(MessageSource.class);
        transactionRepo = mock(TransactionRepo.class);
        pspService = new PspServiceImpl(restClient, messageSource, transactionRepo);
    }
    
    @Test
    public void testProcessPayment_ValidCard() {
        // Prepare test data
        TransactionRequest request = new TransactionRequest();
        request.setCardDetails(new CardDetails("1234567890123456", "12/25", "123", 100.0, "USD", "merchant123"));

        TransactionResponse expectedResponse = new TransactionResponse();
        expectedResponse.setTransactionId("123");

        // Mock the behavior of dependencies
        when(restClient.processTransaction(any(TransactionRequest.class))).thenReturn(expectedResponse);
        when(transactionRepo.findByTransactionId(anyString())).thenReturn(request);

        request.setTransactionId("123");
        request.setCardDetails(new CardDetails("1234567890123452", "12/25", "123", 100.0, "USD", "merchant123"));
        

        // Perform the method under test
        pspService.processPayment(request);

        // Verify the result
        verify(transactionRepo, times(2)).save(any());
        verify(transactionRepo).findByTransactionId(anyString());
        verify(restClient).processTransaction(any(TransactionRequest.class));
        // Assert additional conditions if necessary
    }
    
    @Test
    public void testProcessPayment_InValidCard() {
        // Prepare test data
        TransactionRequest request = new TransactionRequest();
        request.setCardDetails(new CardDetails("1234567890123455", "12/25", "123", 100.0, "USD", "merchant123"));

        TransactionResponse expectedResponse = new TransactionResponse();
        expectedResponse.setTransactionId("123");

        // Mock the behavior of dependencies
        when(restClient.processTransaction(any(TransactionRequest.class))).thenReturn(expectedResponse);
        when(transactionRepo.findByTransactionId(anyString())).thenReturn(request);

        request.setTransactionId("123");
        request.setCardDetails(new CardDetails("1234567890123455", "12/25", "123", 100.0, "USD", "merchant123"));
        

        // Perform the method under test
        pspService.processPayment(request);

    }


}
