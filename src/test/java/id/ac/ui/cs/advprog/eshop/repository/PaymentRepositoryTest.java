package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {

    PaymentRepository paymentRepository;
    List<Payment> payments;
    Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();

        paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        payments = new ArrayList<>();
        Payment payment1 = new Payment("123", "VOUCHER", paymentData);
        payments.add(payment1);
    }
    @Test
    void testSave() {
        Payment payment = payments.get(0);
        Payment result = paymentRepository.save(payment);
        assertEquals(payment.getId(), result.getId());

        Payment findResult = paymentRepository.findById(payments.get(0).getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getStatus(), findResult.getStatus());
    }
    @Test
    void testSaveUpdate() {
        Payment payment = payments.get(0);
        paymentRepository.save(payment);

        Map<String, String> newData = new HashMap<>();
        newData.put("voucherCode", "ESHOPABCDIEFGH");
        Payment newPayment = new Payment(payment.getId(), "VOUCHER", newData, PaymentStatus.SUCCESS.getValue());

        Payment result = paymentRepository.save(newPayment);
        assertEquals(payment.getId(), result.getId());

        Payment findResult = paymentRepository.findById(payment.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), findResult.getStatus());
        assertEquals("ESHOPABCDIEFGH", findResult.getPaymentData().get("voucherCode"));
    }

    @Test
    void testFindByIdIfIdFound() {
        Payment payment = payments.get(0);
        paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(payments.get(0).getId());
        assertEquals(payments.get(0).getId(), findResult.getId());
        assertEquals(payments.get(0).getMethod(), findResult.getMethod());
        assertEquals(payments.get(0).getStatus(), findResult.getStatus());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        paymentRepository.save(payments.get(0));

        Payment findResult = paymentRepository.findById("zczc");
        assertNull(findResult);
    }
    @Test
    void testFindAll() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        List<Payment> paymentList = paymentRepository.findAll();
        assertEquals(payments.size(), paymentList.size());
    }
}