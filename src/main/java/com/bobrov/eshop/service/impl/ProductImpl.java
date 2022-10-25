package com.bobrov.eshop.service.impl;

import com.bobrov.eshop.dao.ProductRepository;
import com.bobrov.eshop.dto.ProductDto;
import com.bobrov.eshop.exception.ProductNotFoundException;
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
                .orElseThrow(ProductNotFoundException::new);

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

        return ProductMapper.INSTANCE.toDto(
                productRepository.save(product)
        );
    }

    @Override
    public ProductDto update(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId())
                .orElseThrow(ProductNotFoundException::new);

        ProductMapper.INSTANCE.updateModel(productDto, product);

        return ProductMapper.INSTANCE.toDto(
                productRepository.save(product)
        );

    }

    @Override
    public void delete(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new ProductNotFoundException(String.format("No product with id %s exists", id));
        }
    }
}
