package com.divergentsl.acquirer.util;

import java.util.Calendar;

public class DateUtil {
	private DateUtil() {
	}

	/**
	 * it is used to validate expiry date format
	 * 
	 * @param expiryDate
	 * @return boolean values
	 */
	public static boolean isValidExpiryDateFormat(String expiryDate) {
		// Check if expiryDate is not null and has the correct length
		if (expiryDate == null || expiryDate.length() != 5) {
			return false;
		}

		// Split the expiryDate into month and year parts
		String[] parts = expiryDate.split("/");
		if (parts.length != 2) {
			return false;
		}

		// Check if month and year parts are numeric
		try {
			int month = Integer.parseInt(parts[0]);
			int year = Integer.parseInt(parts[1]);

			// Check if month is in the valid range (1 to 12) and year is greater than or
			// equal to current year
			int currentYear = Calendar.getInstance().get(Calendar.YEAR) % 100; // Get last two digits of current year
			return month >= 1 && month <= 12 && year >= currentYear;
		} catch (NumberFormatException e) {
			return false; // Parsing error, not numeric
		}
	}

}
