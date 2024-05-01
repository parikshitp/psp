package com.divergentsl.psp.domain;

import java.util.Objects;

import com.divergentsl.psp.annotation.ValidCardNumber;
import com.divergentsl.psp.annotation.ValidExpiryDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CardDetails {

	@ValidCardNumber
	@NotBlank(message = "Card Number can't be null or blank")
	private String cardNumber;

	@ValidExpiryDate
	@NotBlank(message = "Expiry Date can't be null or blank")
	private String expiryDate; // Assuming format "MM/YY"

	@NotBlank(message = "CVV can't be null or blank")
	private String cvv;

	@NotNull(message = "Amount can't be null")
	@Min(value = 1, message = "Amount must be greater than or equal to 1")
	private Double amount;

	@NotBlank(message = "Currency can't be null or blank")
	private String currency;

	@NotBlank(message = "Merchant ID can't be null or blank")
	private String merchantId;

	@Override
	public String toString() {
		return "CardDetails{" + "cardNumber='" + cardNumber + '\'' + ", expiryDate='" + expiryDate + '\'' + ", cvv='"
				+ cvv + '\'' + ", amount=" + amount + ", currency='" + currency + '\'' + ", merchantId='" + merchantId
				+ '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CardDetails that = (CardDetails) o;
		return Double.compare(that.amount, amount) == 0 && Objects.equals(cardNumber, that.cardNumber)
				&& Objects.equals(expiryDate, that.expiryDate) && Objects.equals(cvv, that.cvv)
				&& Objects.equals(currency, that.currency) && Objects.equals(merchantId, that.merchantId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cardNumber, expiryDate, cvv, amount, currency, merchantId);
	}
}
