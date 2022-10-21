package com.bobrov.eshop.dto;

import com.bobrov.eshop.model.Product;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.bobrov.eshop.model.Product} entity
 */
@Data
public class ProductDto implements Serializable {
    // TODO validate
    private Long id;
    private String name;
    private BigDecimal price;
    private Product.ProductStatus status;
}