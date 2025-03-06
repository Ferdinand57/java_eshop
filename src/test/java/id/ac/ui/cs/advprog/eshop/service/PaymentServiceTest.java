package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;
    @Mock
    OrderRepository orderRepository;

    List<Payment> payments;
    List<Product> products;
    Order order;

    @BeforeEach
    void setUp() {
        products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");

        payments = new ArrayList<>();
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment1 = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "VOUCHER", paymentData);
        payments.add(payment1);
    }

    @Test
    void testGetPayment() {
        Payment payment = payments.get(0);
        doReturn(payment).when(paymentRepository).findById(payment.getId());
        Payment result = paymentService.getPayment(payment.getId());
        assertEquals(payment, result);
    }

    @Test
    void testGetAllPayments() {
        doReturn(payments).when(paymentRepository).findAll();

        List<Payment> result = paymentService.getAllPayments();
        assertEquals(payments.size(), result.size());
        assertEquals(payments, result);

    }

    @Test
    void testAddPaymentVoucherSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = paymentService.addPayment(order, "VOUCHER", paymentData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testAddPaymentVoucherRejected() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678");

        Payment payment = paymentService.addPayment(order, "VOUCHER", paymentData);
        assertEquals("REJECTED", payment.getStatus());

        paymentData.put("voucherCode", "ESHP1234ABC5678");
        payment = paymentService.addPayment(order, "VOUCHER", paymentData);
        assertEquals("REJECTED", payment.getStatus());

        paymentData.put("voucherCode", "ESHOPABCDIEF5678");
        payment = paymentService.addPayment(order, "VOUCHER", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testAddPaymentBankTransferRejected() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "");
        paymentData.put("referenceCode", "123456");
        Payment payment = paymentService.addPayment(order, "BANK_TRANSFER", paymentData);
        assertEquals("REJECTED", payment.getStatus());

        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "");
        payment = paymentService.addPayment(order, "BANK_TRANSFER", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testAddPaymentBankTransferSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "123456");
        Payment payment = paymentService.addPayment(order, "BANK_TRANSFER", paymentData);
        assertNull(payment.getStatus());
    }
}