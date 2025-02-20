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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("test-product");
        product.setProductName("Test Product");
        product.setProductQuantity(5);
    }

    @Test
    void testCreateProduct() {
        when(productRepository.create(product)).thenReturn(product); // Mock repository behavior

        Product createdProduct = productService.create(product);

        assertNotNull(createdProduct);
        assertEquals(product.getProductId(), createdProduct.getProductId());
        assertEquals(product.getProductName(), createdProduct.getProductName());
        assertEquals(product.getProductQuantity(), createdProduct.getProductQuantity());
        verify(productRepository, times(1)).create(product); // Verify repository method was called
    }

    @Test
    void testFindAllProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(new Product()); // Add another product

        when(productRepository.findAll()).thenReturn(productList.iterator()); // Mock repository findAll

        List<Product> allProducts = productService.findAll();

        assertNotNull(allProducts);
        assertEquals(productList.size(), allProducts.size());
        Iterator<Product> expectedIterator = productList.iterator();
        for (Product actualProduct : allProducts) {
            assertTrue(expectedIterator.hasNext());
            Product expectedProduct = expectedIterator.next();
            assertEquals(expectedProduct, actualProduct); // Check product equality
        }
        verify(productRepository, times(1)).findAll(); // Verify repository method was called
    }
}