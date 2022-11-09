package com.bobrov.eshop.service;

import com.bobrov.eshop.dto.OrderDto;
import com.bobrov.eshop.model.Order;

import java.util.List;

public interface OrderService {
    OrderDto findById(Long id);

    List<Order> findAll(Integer offset, Integer limit);

    List<Order> findByCreatedAtBetweenOrderByIdAscCreatedAtAsc(String createdAtStart, String createdAtEnd);

    OrderDto save(OrderDto order);

    OrderDto update(OrderDto orderDto);

    void delete(Long id);
}
