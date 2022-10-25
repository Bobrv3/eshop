package com.bobrov.eshop.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class ProductJoinOrderDto {
    private final String name;
    private final BigDecimal price;
    private final Long quantity;
}
