package com.divergentsl.psp.util;

import java.util.UUID;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class TransactionUtil {

	public static String generateTransactionId(String merchantId, String cardNumber) {
		String txId = UUID.randomUUID().toString();
		log.info("Inside TransactionUtil generateTransactionId {}", txId);
		return txId;
	}

}
