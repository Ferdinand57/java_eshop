package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void testCreateProductPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("createProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void testCreateProductPost() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/product/create")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("productName", "Test Product")
                        .param("productQuantity", "10"))
                .andExpect(status().is3xxRedirection()) // Expect redirect
                .andExpect(redirectedUrl("list"));
        verify(productService, times(1)).create(Mockito.any(Product.class)); // Verify service create called
    }

    @Test
    void testProductListPage() throws Exception {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());
        Mockito.when(productService.findAll()).thenReturn(productList); // Mock service findAll

        mockMvc.perform(MockMvcRequestBuilders.get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("productList"))
                .andExpect(model().attributeExists("products"))
                .andExpect(model().attribute("products", productList)); // Check model attribute value
        verify(productService, times(1)).findAll(); // Verify service findAll called
    }
}