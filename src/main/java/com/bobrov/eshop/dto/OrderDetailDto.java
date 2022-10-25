package com.bobrov.eshop.dto;

import com.bobrov.eshop.model.OrderDetailId;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.bobrov.eshop.model.OrderDetail} entity
 */
@Data
public class OrderDetailDto implements Serializable {
    private final OrderDetailId id;
    private final ProductDto product;
    private final int quantity;
}