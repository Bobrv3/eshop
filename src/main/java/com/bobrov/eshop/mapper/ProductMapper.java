package com.bobrov.eshop.mapper;

import com.bobrov.eshop.dto.ProductDto;
import com.bobrov.eshop.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(imports = {LocalDateTime.class})
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toProduct(ProductDto productDto);

    ProductDto toDto(Product product);

    List<Product> toProductList(List<ProductDto> productDtos);

    List<ProductDto> toDtoList(List<Product> products);

    void updateModel(ProductDto productDto, @MappingTarget Product product);
}
