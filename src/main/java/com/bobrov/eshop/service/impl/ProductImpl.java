package com.bobrov.eshop.service.impl;

import com.bobrov.eshop.dao.ProductRepository;
import com.bobrov.eshop.dto.ProductDto;
import com.bobrov.eshop.mapper.ProductMapper;
import com.bobrov.eshop.model.Product;
import com.bobrov.eshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public List<Product> findAll(Integer offset, Integer limit) {
        Page<Product> products = productRepository.findAll(PageRequest.of(offset, limit));
        return products.getContent();
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        Product product = ProductMapper.INSTANCE.toProduct(productDto);
        product.setStatus(Product.ProductStatus.IN_STOCK);

        return ProductMapper.INSTANCE.toDto(
                productRepository.save(product)
        );
    }

    @Override
    public ProductDto update(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId()).get();

        ProductMapper.INSTANCE.updateModel(productDto, product);

        return ProductMapper.INSTANCE.toDto(
                productRepository.save(product)
        );

    }

    @Override
    public boolean delete(Long id) {
        productRepository.deleteById(id);
        return true;
    }
}
