package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
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
    void testSetStatusSuccess() {
        Payment payment = new Payment("123", "VOUCHER", this.paymentData);
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testSetStatusRejected() {
        Payment payment = new Payment("123", "VOUCHER", this.paymentData);
        payment.setStatus(PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetStatusInvalid() {
        Payment payment = new Payment("123", "VOUCHER", this.paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("INVALID"));
    }

    @Test
    void testCreatePaymentNullData() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("123", "VOUCHER", null);
        });
    }

    @Test
    void testCreatePaymentEmptyData() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("123", "VOUCHER", new HashMap<>());
        });
    }
}