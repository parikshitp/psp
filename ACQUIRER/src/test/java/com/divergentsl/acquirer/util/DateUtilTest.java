package com.divergentsl.acquirer.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DateUtilTest {

    @Test
    public void testIsValidExpiryDateFormat_ValidFormat() {
        // Valid expiry date format: MM/YY
        assertTrue(DateUtil.isValidExpiryDateFormat("12/25"));
        assertTrue(DateUtil.isValidExpiryDateFormat("01/26"));
        assertTrue(DateUtil.isValidExpiryDateFormat("03/27"));
        assertTrue(DateUtil.isValidExpiryDateFormat("12/30"));
    }

    @Test
    public void testIsValidExpiryDateFormat_InvalidFormat() {
        // Invalid expiry date format: Incorrect length
        assertFalse(DateUtil.isValidExpiryDateFormat(null));
        assertFalse(DateUtil.isValidExpiryDateFormat(""));
        assertFalse(DateUtil.isValidExpiryDateFormat("123/4567")); // Invalid length
        assertFalse(DateUtil.isValidExpiryDateFormat("12/345")); // Invalid length

        // Invalid expiry date format: Non-numeric characters
        assertFalse(DateUtil.isValidExpiryDateFormat("12/ab"));
        assertFalse(DateUtil.isValidExpiryDateFormat("12/20a"));
        assertFalse(DateUtil.isValidExpiryDateFormat("ab/25"));

        // Invalid expiry date format: Out of range
        assertFalse(DateUtil.isValidExpiryDateFormat("00/25"));
        assertFalse(DateUtil.isValidExpiryDateFormat("13/25"));
        assertFalse(DateUtil.isValidExpiryDateFormat("01/22")); // Year in the past
        assertFalse(DateUtil.isValidExpiryDateFormat("12/30/40"));
        assertFalse(DateUtil.isValidExpiryDateFormat("12/"));
        assertFalse(DateUtil.isValidExpiryDateFormat("23"));
    }
    
    @Test
    public void testIsValidExpiryDateFormat_ExtraDelimiter() {
        // Invalid expiry date format: Extra delimiter at the end
        assertFalse(DateUtil.isValidExpiryDateFormat("13/2/"));
        assertFalse(DateUtil.isValidExpiryDateFormat("/////"));
    }

}
