package com.bobrov.eshop.service.impl;

import com.bobrov.eshop.dao.ProductRepository;
import com.bobrov.eshop.dto.ProductDto;
import com.bobrov.eshop.mapper.ProductMapper;
import com.bobrov.eshop.model.Product;
import com.bobrov.eshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return ProductMapper.INSTANCE.toDto(product);
    }

    @Override
    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();

        return ProductMapper.INSTANCE.toDtoList(products);
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        Product product = productRepository.save(ProductMapper.INSTANCE.toProduct(productDto));

        return ProductMapper.INSTANCE.toDto(product);
    }

    @Override
    public boolean delete(Long id) {
        productRepository.deleteById(id);
        return true;
    }
}
