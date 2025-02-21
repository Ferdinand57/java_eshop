package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void testCreateProductPage() throws Exception {
        final String viewName = productController.createProductPage(model);
        assertEquals("createProduct", viewName, "View name should be createProduct");
    }

    @Test
    void testCreateProductPost() throws Exception {
        final Product product = new Product();
        final String redirectUrl = productController.createProductPost(product, model);
        assertEquals("redirect:list", redirectUrl, "Redirect URL should be redirect:list");
    }

    @Test
    void testProductListPage() throws Exception {
        final List<Product> productList = new ArrayList<>();
        when(productService.findAll()).thenReturn(productList);
        final String viewName = productController.productListPage(model);
        assertEquals("productList", viewName, "View name should be productList");
    }

    @Test
    void productListPageLawOfDemeter() throws Exception {
        try {
            final List<Product> productList = new ArrayList<>();
            when(productService.findAll()).thenReturn(productList);

            final ResultActions result = mockMvc.perform(get("/product/list"));
            result.andExpect(status().isOk());
            result.andExpect(view().name("productList"));
        } catch (Exception e) {
            fail("Exception during test: " + e.getMessage()); // Or handle the exception appropriately
        }
    }
}