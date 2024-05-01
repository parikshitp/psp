package com.divergentsl.acquirer.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TransactionUtilTest {

    @Test
    void testIsCardValidated_EvenLastDigit() {
        // Arrange
        String cardNumber = "378734493671000"; // Even last digit

        // Act
        boolean result = TransactionUtil.isCardValidated(cardNumber);

        // Assert
        assertTrue(result, "Expected card validation to be true for even last digit");
    }

    @Test
    void testIsCardValidated_OddLastDigit() {
        // Arrange
        String cardNumber = "378282246310005"; // Odd last digit

        // Act
        boolean result = TransactionUtil.isCardValidated(cardNumber);

        // Assert
        assertFalse(result, "Expected card validation to be false for odd last digit");
    }
}

