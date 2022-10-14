package com.bobrov.eshop.service;

import com.bobrov.eshop.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto findById(Long id);

    List<ProductDto> findAll();

    ProductDto save(ProductDto productDto);

    boolean delete(Long id);
}
