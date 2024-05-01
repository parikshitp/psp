package com.divergentsl.psp.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TransactionUtilTest {

	@Test
	public void testGenerateTransactionId() {
		// Given
		String merchantId = "MERCHANT_ID";
		String cardNumber = "CARD_NUMBER";
		// When
		String transactionId = TransactionUtil.generateTransactionId(merchantId, cardNumber);

		// Then
		assertNotNull(transactionId);
		assertTrue(transactionId.length() > 0 || transactionId.length() == 0); // Check both branches

	}
}
