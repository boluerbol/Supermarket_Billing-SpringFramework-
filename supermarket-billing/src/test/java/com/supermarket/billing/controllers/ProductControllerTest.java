package com.supermarket.billing.controllers;

import com.supermarket.billing.entity.Product;
import com.supermarket.billing.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    public void testGetAllProducts() throws Exception {
        List<Product> products = Arrays.asList(
                new Product(1L, "Product 1", 10.0, new HashSet<>()),
                new Product(2L, "Product 2", 15.0, new HashSet<>())
        );
        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(products.size()));
    }

    @Test
    public void testGetProductById() throws Exception {
        Product product = new Product(1L, "Product 1", 10.0, new HashSet<>());
        when(productService.getProductById(1L)).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(product.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(product.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(product.getPrice()));
    }

    @Test
    public void testCreateProduct() throws Exception {
        Product product = new Product(1L, "Product 1", 10.0, new HashSet<>());
        when(productService.createProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Product 1\",\"price\":10.0}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(product.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(product.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(product.getPrice()));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Product product = new Product(1L, "Product 1", 10.0, new HashSet<>());
        when(productService.updateProduct(1L, product)).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Product 1\",\"price\":10.0}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(product.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(product.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(product.getPrice()));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/products/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
