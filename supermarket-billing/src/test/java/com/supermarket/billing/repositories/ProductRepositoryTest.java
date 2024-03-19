package com.supermarket.billing.repositories;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.supermarket.billing.entity.Product;
import com.supermarket.billing.repositories.ProductRepository;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSaveProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(10.0);
        productRepository.save(product);

        assertNotNull(product.getId());
    }

    @Test
    public void testFindProductById() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(10.0);
        productRepository.save(product);
        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        assertTrue(optionalProduct.isPresent());
        assertEquals(product, optionalProduct.get());
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(10.0);
        productRepository.save(product);
        product.setPrice(15.0);
        productRepository.save(product);

        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        assertTrue(optionalProduct.isPresent());
        assertEquals(15.0, optionalProduct.get().getPrice());
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(10.0);
        productRepository.save(product);

        productRepository.delete(product);

        assertFalse(productRepository.findById(product.getId()).isPresent());
    }

    @Test
    public void testFindAllProducts() {
        Product product1 = new Product();
        product1.setName("Product 1");
        product1.setPrice(20.0);
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Product 2");
        product2.setPrice(30.0);
        productRepository.save(product2);

        List<Product> products = (List<Product>) productRepository.findAll();
        assertEquals(2, products.size());
    }
}
