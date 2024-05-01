package com.divergentsl.acquirer.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.divergentsl.acquirer.domain.CardDetails;

class CardDetailsTest {

	@Test
	void testEquals_SameObject() {
		// Arrange
		CardDetails cardDetails = new CardDetails("1234567890123456", "12/23", "123", 100.0, "USD", "merchant123");

		// Act & Assert
		assertEquals(cardDetails, cardDetails, "Equals method should return true for the same object");
	}

	@Test
	void testEquals_NullObject() {
		// Arrange
		CardDetails cardDetails = new CardDetails("1234567890123456", "12/23", "123", 100.0, "USD", "merchant123");

		// Act & Assert
		assertNotEquals(cardDetails, null, "Equals method should return false for null");
	}

	@Test
	void testEquals_DifferentClass() {
		// Arrange
		CardDetails cardDetails = new CardDetails("1234567890123456", "12/23", "123", 100.0, "USD", "merchant123");

		// Act & Assert
		assertNotEquals(cardDetails, new Object(), "Equals method should return false for different class");
	}

	@Test
	void testEquals_SameValues() {
		// Arrange
		CardDetails cardDetails1 = new CardDetails("1234567890123456", "12/23", "123", 100.0, "USD", "merchant123");
		CardDetails cardDetails2 = new CardDetails("1234567890123456", "12/23", "123", 100.0, "USD", "merchant123");

		// Act & Assert
		assertEquals(cardDetails1, cardDetails2, "Equals method should return true for same values");
	}

	@Test
	void testEquals_DifferentValues() {
		// Arrange
		CardDetails cardDetails1 = new CardDetails("1234567890123456", "12/23", "123", 100.0, "USD", "merchant123");
		CardDetails cardDetails2 = new CardDetails("9876543210987654", "12/24", "456", 200.0, "EUR", "merchant456");

		// Act & Assert
		assertNotEquals(cardDetails1, cardDetails2, "Equals method should return false for different values");
	}

	@Test
	void testHashCode() {
		// Arrange
		CardDetails cardDetails1 = new CardDetails("1234567890123456", "12/23", "123", 100.0, "USD", "merchant123");
		CardDetails cardDetails2 = new CardDetails("1234567890123456", "12/23", "123", 100.0, "USD", "merchant123");

		// Act & Assert
		assertEquals(cardDetails1.hashCode(), cardDetails2.hashCode(), "Hash codes should be equal for same values");
	}

	@Test
	void testToString() {
		// Arrange
		CardDetails cardDetails = new CardDetails("1234567890123456", "12/23", "123", 100.0, "USD", "merchant123");

		// Act & Assert
		assertEquals(
				"CardDetails{cardNumber='1234567890123456', expiryDate='12/23', cvv='123', amount=100.0, currency='USD', merchantId='merchant123'}",
				cardDetails.toString());
	}

	@Test
	void testSetExpiryDate_ValidFormat() {
		// Arrange
		CardDetails cardDetails = new CardDetails("1234567890123456", "12/23", "123", 100.0, "USD", "merchant123");

		// Act
		cardDetails.setExpiryDate("01/25");

		// Assert
		assertEquals("01/25", cardDetails.getExpiryDate(), "Expiry date should be set to the new value");
	}

}
