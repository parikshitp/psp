package com.divergentsl.acquirer.annotation.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import com.divergentsl.psp.annotation.impl.ExpiryDateValidator;

@Configuration // Dummy configuration class
class ExpiryDateValidatorTestConfig {
}

@SpringBootTest(classes = { ExpiryDateValidatorTestConfig.class })
public class ExpiryDateValidatorTests {

	private ExpiryDateValidator expiryDateValidator = new ExpiryDateValidator();

	@Test
	public void testIsValidExpiryDateFormat_ValidFormat() {
		// Valid expiry date format: MM/YY
		assertTrue(expiryDateValidator.isValid("12/25", null));
		assertTrue(expiryDateValidator.isValid("01/26", null));
		assertTrue(expiryDateValidator.isValid("03/27", null));
		assertTrue(expiryDateValidator.isValid("12/30", null));
	}

	@Test
	public void testIsValidExpiryDateFormat_InvalidFormat() {
		// Invalid expiry date format: Incorrect length
		assertFalse(expiryDateValidator.isValid(null, null));
		assertFalse(expiryDateValidator.isValid("", null));
		assertFalse(expiryDateValidator.isValid("123/4567", null)); // Invalid length
		assertFalse(expiryDateValidator.isValid("12/345", null)); // Invalid length

		// Invalid expiry date format: Non-numeric characters
		assertFalse(expiryDateValidator.isValid("12/ab", null));
		assertFalse(expiryDateValidator.isValid("12/20a", null));
		assertFalse(expiryDateValidator.isValid("ab/25", null));

		// Invalid expiry date format: Out of range
		assertFalse(expiryDateValidator.isValid("00/25", null));
		assertFalse(expiryDateValidator.isValid("13/25", null));
		assertFalse(expiryDateValidator.isValid("01/22", null)); // Year in the past
		assertFalse(expiryDateValidator.isValid("12/30/40", null));
		assertFalse(expiryDateValidator.isValid("12/", null));
		assertFalse(expiryDateValidator.isValid("23", null));
	}
}
