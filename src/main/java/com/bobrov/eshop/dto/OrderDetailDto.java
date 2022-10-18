package com.bobrov.eshop.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.bobrov.eshop.model.OrderDetail} entity
 */
@Data
public class OrderDetailDto implements Serializable {

    final ProductDto product;
    private final int quantity;
}