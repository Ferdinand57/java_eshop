package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class PaymentTest {

    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();
        this.paymentData.put("voucherCode", "ESHOP1234ABC5678");
    }

    @Test
    void testCreatePayment() {
        Payment payment = new Payment("123", "VOUCHER", this.paymentData);
        assertEquals("123", payment.getId());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals(this.paymentData, payment.getPaymentData());
        assertNull(payment.getStatus());
    }

    @Test
    void testSetStatus() {
        Payment payment = new Payment("123", "VOUCHER", this.paymentData);
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentNullData() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("123", "VOUCHER", null);
        });
    }

    @Test
    void testCreatePaymentEmptyData() {
        Map<String, String> emptyData = new HashMap<>();
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("123", "VOUCHER", emptyData);
        });
    }
}