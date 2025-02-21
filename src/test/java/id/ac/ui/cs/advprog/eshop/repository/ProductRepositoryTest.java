package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
    }
    @Test
    void testCreateAndFind() {
        final Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        final Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext(), "Product should be found");
        final Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId(), "Product ID should match");
        assertEquals(product.getProductName(), savedProduct.getProductName(), "Product Name should match");
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity(), "Product Quantity should match");
    }

    @Test
    void testFindAllIfEmpty() {
        final Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext(), "Iterator should be empty");
    }
    @Test
    void testFindAllIfMoreThanOneProduct() {
        final Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        final Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        final Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext(), "First product should be found");
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId(), "Product ID should match");
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId(), "Product ID should match");
        assertFalse(productIterator.hasNext(), "Iterator should have only two products");
    }
}