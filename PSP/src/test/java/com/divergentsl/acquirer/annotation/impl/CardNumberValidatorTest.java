package com.divergentsl.acquirer.annotation.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import com.divergentsl.psp.annotation.impl.CardNumberValidator;

@Configuration // Dummy configuration class
class CardNumberValidatorTestConfig {
}

@SpringBootTest(classes = { CardNumberValidatorTestConfig.class })
public class CardNumberValidatorTest {

	private CardNumberValidator cardNumberValidator = new CardNumberValidator();

	@Test
	public void testIsValidCardNumber_ValidCardNumbers() {
		// Valid card numbers
		assertTrue(cardNumberValidator.isValid("378282246310005", null));
		assertTrue(cardNumberValidator.isValid("5610591081018250", null));
		assertTrue(cardNumberValidator.isValid("378734493671000", null));
	}

	@Test
	public void testIsValidCardNumber_InvalidCardNumbers() {
		assertFalse(cardNumberValidator.isValid("3787344936710005", null));
		assertFalse(cardNumberValidator.isValid("4111111111111112", null)); // Luhn algorithm failure
		assertFalse(cardNumberValidator.isValid("5105105105105101", null)); // Luhn algorithm failure
		assertFalse(cardNumberValidator.isValid("378282246310006", null)); // Luhn algorithm failure
		assertFalse(cardNumberValidator.isValid("abcdefghijabcdefgh", null)); // Non-numeric characters
	}
}
