package com.supermarket.billing.controllers;

import com.supermarket.billing.entity.Product;
import com.supermarket.billing.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @Test
    public void testGetAllProducts() throws Exception {
        List<Product> products = Arrays.asList(
                new Product(1L, "Product 1", 10.0, new HashSet<>()),
                new Product(2L, "Product 2", 15.0, new HashSet<>())
        );
        when(productService.getAllProducts()).thenReturn(products);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products"))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(products.size()));
    }

    @Test
    public void testGetProductById() throws Exception {
        Product product = new Product(1L, "Product 1", 10.0, new HashSet<>());
        when(productService.getProductById(1L)).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/1"))
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

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/products")
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
        when(productService.updateProduct(any(), any(Product.class))).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Product 1\",\"price\":10.0}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(product.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(product.getPrice()));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/products/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
