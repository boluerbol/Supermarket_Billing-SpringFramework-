package com.supermarket.billing.mappers;

import com.supermarket.billing.dto.ProductDTO;
import com.supermarket.billing.entity.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "price", ignore = true)
    ProductDTO productToProductDTO(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "price", ignore = true)
    Product productDTOToProduct(ProductDTO productDTO);
}
