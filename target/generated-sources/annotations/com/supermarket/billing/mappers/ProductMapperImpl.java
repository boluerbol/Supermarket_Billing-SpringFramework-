package com.supermarket.billing.mappers;

import com.supermarket.billing.dto.ProductDTO;
import com.supermarket.billing.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-02T10:16:19+0600",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO productToProductDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO.ProductDTOBuilder productDTO = ProductDTO.builder();

        productDTO.id( product.getId() );
        productDTO.name( product.getName() );
        productDTO.price( product.getPrice() );

        return productDTO.build();
    }

    @Override
    public Product productDTOToProduct(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.id( productDTO.getId() );
        product.name( productDTO.getName() );
        product.price( productDTO.getPrice() );

        return product.build();
    }
}
