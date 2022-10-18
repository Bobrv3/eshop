package com.bobrov.eshop.service;

import com.bobrov.eshop.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto findById(Long id);

    List<OrderDto> findAll();
}
