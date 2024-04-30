package com.divergentsl.acquirer.domain;

import java.util.Objects;

import com.divergentsl.acquirer.exception.InvalidExpiryDateException;
import com.divergentsl.acquirer.util.DateUtil;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDetails {

	@NonNull
	private String cardNumber;

	@NonNull
	private String expiryDate; // Assuming format "MM/YY"

	@NonNull
	private String cvv;

	private double amount;

	@NonNull
	private String currency;

	@NonNull
	private String merchantId;

	public void setExpiryDate(String expiryDate) {
		if (!DateUtil.isValidExpiryDateFormat(expiryDate)) {
			throw new InvalidExpiryDateException("error.invalid.expiry.date", new Object[] {});
		}
		this.expiryDate = expiryDate;
	}

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
