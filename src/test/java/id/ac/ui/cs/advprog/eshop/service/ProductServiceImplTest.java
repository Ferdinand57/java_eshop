package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl service;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateProduct() {
        final Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(productRepository.create(product)).thenReturn(product);

        final Product createdProduct = service.create(product);

        assertNotNull(createdProduct, "Created product should not be null");
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", createdProduct.getProductId(), "Product ID should match");
        assertEquals("Sampo Cap Bambang", createdProduct.getProductName(), "Product Name should match");
        assertEquals(100, createdProduct.getProductQuantity(), "Product Quantity should match");
    }

    @Test
    void testFindAllProductIfEmpty() {
        when(productRepository.findAll()).thenReturn(new ArrayList<Product>().iterator());

        final List<Product> productList = service.findAll();

        assertTrue(productList.isEmpty(), "Product list should be empty");
    }

    @Test
    void testFindAllProduct() {
        final List<Product> productData = new ArrayList<>();
        final Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productData.add(product1);

        final Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productData.add(product2);

        when(productRepository.findAll()).thenReturn(productData.iterator());

        final List<Product> allProducts = service.findAll();

        assertFalse(allProducts.isEmpty(), "Product list should not be empty");
        assertEquals(2, allProducts.size(), "Product list should have two products");

        final Iterator<Product> expectedIterator = productData.iterator();
        for (final Product actualProduct : allProducts) {
            final Product expectedProduct = expectedIterator.next();
            assertEquals(expectedProduct.getProductId(), actualProduct.getProductId(), "Product ID should match");
            assertEquals(expectedProduct.getProductName(), actualProduct.getProductName(), "Product Name should match");
            assertEquals(expectedProduct.getProductQuantity(), actualProduct.getProductQuantity(), "Product Quantity should match");
        }
    }
}