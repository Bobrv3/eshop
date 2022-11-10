package com.bobrov.eshop.service.impl;

import com.bobrov.eshop.dao.ProductRepository;
import com.bobrov.eshop.dto.ProductDto;
import com.bobrov.eshop.exception.ProductNotFoundException;
import com.bobrov.eshop.mapper.ProductMapper;
import com.bobrov.eshop.model.Product;
import com.bobrov.eshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductDto findById(Long id) {
        Product product = productRepository.findByIdAndStatusNot(id, Product.ProductStatus.REMOVED)
                .orElseThrow(ProductNotFoundException::new);

        return ProductMapper.INSTANCE.toDto(product);
    }

    @Override
    public List<Product> findAll(Integer offset, Integer limit) {
        return productRepository.findByStatusNot(Product.ProductStatus.REMOVED, PageRequest.of(offset, limit));
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
        ProductDto product = findById(id);

        if (product.getStatus() != Product.ProductStatus.IN_STOCK
                && product.getStatus() != Product.ProductStatus.RUNNING_LOW
        ) {
            productRepository.updateStatusById(Product.ProductStatus.REMOVED, id);
        } else {
            throw new RuntimeException("Can't delete a product in the IN_STOCK and RUNNING_LOW");
        }
    }
}
