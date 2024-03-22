package com.supermarket.billing.services;

import com.supermarket.billing.entity.Product;
import com.supermarket.billing.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceJPA implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceJPA(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> optionalExistingProduct = productRepository.findById(id);
        if (optionalExistingProduct.isPresent()) {
            product.setId(id);
            return productRepository.save(product);
        } else {
            return null; // or throw an exception indicating that the product with the given id doesn't exist
        }
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
