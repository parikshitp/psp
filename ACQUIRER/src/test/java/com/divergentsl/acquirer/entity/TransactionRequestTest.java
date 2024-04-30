package com.divergentsl.acquirer.entity;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.divergentsl.acquirer.domain.CardDetails;
import com.divergentsl.acquirer.domain.TransactionRequest;

class TransactionRequestTest {

    @Test
    void testEquals_SameObject() {
        // Arrange
        TransactionRequest request = new TransactionRequest("123", new CardDetails(), "STATUS");

        // Act & Assert
        assertTrue(request.equals(request), "Equals method should return true for the same object");
    }

    @Test
    void testEquals_NullObject() {
        // Arrange
        TransactionRequest request = new TransactionRequest("123", new CardDetails(), "STATUS");

        // Act & Assert
        assertFalse(request.equals(null), "Equals method should return false for null");
    }

    @Test
    void testEquals_DifferentClass() {
        // Arrange
        TransactionRequest request = new TransactionRequest("123", new CardDetails(), "STATUS");

        // Act & Assert
        assertFalse(request.equals(new Object()), "Equals method should return false for different class");
    }

    @Test
    void testEquals_SameValues() {
        // Arrange
        TransactionRequest request1 = new TransactionRequest("123", new CardDetails(), "STATUS");
        TransactionRequest request2 = new TransactionRequest("123", new CardDetails(), "STATUS");

        // Act & Assert
        assertTrue(request1.equals(request2), "Equals method should return true for same values");
    }

    @Test
    void testEquals_DifferentValues() {
        // Arrange
        TransactionRequest request1 = new TransactionRequest("123", new CardDetails(), "STATUS1");
        TransactionRequest request2 = new TransactionRequest("456", new CardDetails(), "STATUS2");

        // Act & Assert
        assertFalse(request1.equals(request2), "Equals method should return false for different values");
    }

    @Test
    void testHashCode() {
        // Arrange
        TransactionRequest request1 = new TransactionRequest("123", new CardDetails(), "STATUS");
        TransactionRequest request2 = new TransactionRequest("123", new CardDetails(), "STATUS");

        // Act & Assert
        assertEquals(request1.hashCode(), request2.hashCode(), "Hash codes should be equal for same values");
    }

    @Test
    void testToString() {
        // Arrange
        TransactionRequest request = new TransactionRequest("123", new CardDetails(), "STATUS");

        // Act & Assert
        assertEquals("TransactionRequest{transactionId='123', cardDetails=CardDetails{cardNumber='null', expiryDate='null', cvv='null', amount=0.0, currency='null', merchantId='null'}, transactionStatus='STATUS'}", request.toString());
    }
}
