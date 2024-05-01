package com.divergentsl.psp.annotation.impl;

import com.divergentsl.psp.annotation.ValidCardNumber;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CardNumberValidator implements ConstraintValidator<ValidCardNumber, String> {
	@Override
	public void initialize(ValidCardNumber constraintAnnotation) {
	}

	@Override
	public boolean isValid(String cardNo, ConstraintValidatorContext constraintValidatorContext) {
		log.info("Inside CardNumberValidator isValid method: {} ", cardNo);
		int nDigits = cardNo.length();

		int nSum = 0;
		boolean isSecond = false;
		for (int i = nDigits - 1; i >= 0; i--) {

			int d = cardNo.charAt(i) - '0';

			if (isSecond == true)
				d = d * 2;

			// We add two digits to handle
			// cases that make two digits
			// after doubling
			nSum += d / 10;
			nSum += d % 10;

			isSecond = !isSecond;
		}
		return (nSum % 10 == 0);

	}
}
