package com.supermarket.billing.mappers;

import com.supermarket.billing.dto.ProductDTO;
import com.supermarket.billing.entity.Product;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductMapperTest {

    private final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @Test
    public void testProductToProductDTO() {
        // Given
        Product product = Product.builder()
            .id(1L)
            .name("Test Product")
            .price(10.5)
            .build();


        // When
        ProductDTO productDTO = mapper.productToProductDTO(product);

        // Then
        assertEquals(product.getId(), productDTO.getId());
        assertEquals(product.getName(), productDTO.getName());
        assertEquals(product.getPrice(), productDTO.getPrice());
    }

    @Test
    public void testProductDTOToProduct() {
        // Given
        ProductDTO productDTO = ProductDTO.builder()
                .id(1L)
                .name("Test Product")
                .price(10.5)
                .build();

        // When
        Product product = mapper.productDTOToProduct(productDTO);

        // Then
        assertEquals(productDTO.getId(), product.getId());
        assertEquals(productDTO.getName(), product.getName());
        assertEquals(productDTO.getPrice(), product.getPrice());
    }
}
