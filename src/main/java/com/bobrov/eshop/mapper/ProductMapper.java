package com.bobrov.eshop.mapper;

import com.bobrov.eshop.dto.ProductDto;
import com.bobrov.eshop.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(imports = {LocalDateTime.class})
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "status", expression = "java(Product.ProductStatus.IN_STOCK)")
    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    Product toProduct(ProductDto productDto);

    ProductDto toDto(Product product);

    List<Product> toProductList(List<ProductDto> productDtos);

    List<ProductDto> toDtoList(List<Product> products);

}
