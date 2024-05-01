package com.divergentsl.psp.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

	private String transactionId;

	@Valid
	@NotNull
	private CardDetails cardDetails;

	private String transactionStatus;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		TransactionRequest that = (TransactionRequest) o;

		if (transactionId != null ? !transactionId.equals(that.transactionId) : that.transactionId != null)
			return false;
		return cardDetails != null ? cardDetails.equals(that.cardDetails) : that.cardDetails == null;
	}

	@Override
	public int hashCode() {
		int result = transactionId != null ? transactionId.hashCode() : 0;
		result = 31 * result + (cardDetails != null ? cardDetails.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "TransactionRequest{" + "transactionId='" + transactionId + '\'' + ", cardDetails=" + cardDetails
				+ ", transactionStatus='" + transactionStatus + '\'' + '}';
	}
}
