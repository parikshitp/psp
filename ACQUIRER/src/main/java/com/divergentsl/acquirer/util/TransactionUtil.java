package com.divergentsl.acquirer.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TransactionUtil {

	/**
	 * it is used to validate card details
	 * 
	 * @param cardNumber
	 * @return boolean
	 */
	public static boolean isCardValidated(String cardNumber) {
		log.info("Inside TransactionUtil method isApproved: {}", cardNumber);
		// Simulate mock acquirer's decision (odd last digit: deny, even last digit:
		// approve)
		int lastDigit = Character.getNumericValue(cardNumber.charAt(cardNumber.length() - 1));
		return lastDigit % 2 == 0;
	}

}
