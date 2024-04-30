package com.divergentsl.psp.util;

import java.time.Instant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TransactionUtil {

	public static boolean isApproved(String cardNumber) {
		log.info("Inside TransactionUtil method isApproved: {}", cardNumber);
		// Simulate mock acquirer's decision (odd last digit: deny, even last digit:
		// approve)
		int lastDigit = Character.getNumericValue(cardNumber.charAt(cardNumber.length() - 1));
		return lastDigit % 2 == 0;
	}
	
	public static boolean isValidCardLuhansAlgorithm(String cardNumber) {
        // Remove any non-digit characters
        cardNumber = cardNumber.replaceAll("\\D", "");

        // Check if the card number is empty or not a number
        if (cardNumber.isEmpty() || !cardNumber.matches("\\d+")) {
            return false;
        }

        // Reverse the card number
        StringBuilder reversedCardNumber = new StringBuilder(cardNumber).reverse();

        int sum = 0;
        boolean alternate = false;
        for (int i = 0; i < reversedCardNumber.length(); i++) {
            int digit = Character.getNumericValue(reversedCardNumber.charAt(i));
            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
            alternate = !alternate;
        }

        // Check if the sum is divisible by 10
        return sum % 10 == 0;
    }

	public static String generateTransactionId(String merchantId, String cardNumber) {
		// Extracting the last 6 digits of the card number
		String lastSixDigits = cardNumber.substring(cardNumber.length() - 6);

		// Concatenating the parts to form the transaction ID
		return "TXN" + merchantId + "TD" + Instant.now().toEpochMilli() + lastSixDigits;
	}

}
