package com.bobrov.eshop.service;

import com.bobrov.eshop.dto.ProductDto;
import com.bobrov.eshop.model.Product;

import java.util.List;

public interface ProductService {
    ProductDto findById(Long id);

    List<Product> findAll(Integer offset, Integer limit);

    ProductDto save(ProductDto productDto);

    ProductDto update(ProductDto productDto);

    void delete(Long id);
}
